package com.barmjz.productivityapp.Note;

import com.barmjz.productivityapp.Folder.Folder;
import com.barmjz.productivityapp.Folder.FolderRepo;
import com.barmjz.productivityapp.user.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteManager {

    private final NoteRepo noteRepo;
    private final FolderRepo folderRepo;
    private final UserRepo userRepo;

    public Note createNote(Long folderId, String noteTitle){
        if(folderId == null)
            throw new NullPointerException("folderId is null");
        Optional<Folder> folder = folderRepo.findById(folderId);
        if(!folder.isPresent())
            throw new NoSuchElementException("folder not found");
        Date date = new Date();
        // check default values
        Note newNote = Note.builder()
                .title(noteTitle)
                .content("")
                .starred(false)
                .folder(folder.get())
                .createdDate(date)
                .modifiedDate(date)
                .build();
        if(noteRepo.existsByTitleAndFolder_Id(noteTitle,folder.get().getId()))
            throw new IllegalStateException("note title already exist");
        noteRepo.save(newNote);
        return newNote;
    }

    public String modifyNote(Long noteId, String title, String content){
        if(noteId == null || !noteRepo.existsById(noteId))
            throw new NoSuchElementException("note not found");
        Note existedNote = noteRepo.getReferenceById(noteId);
        if(    (noteRepo.existsByTitleAndFolder_Id(title,existedNote.getFolder().getId())) &&
                (!noteRepo.findByTitleAndFolder_Id(title,existedNote.getFolder().getId()).getId().equals(existedNote.getId())))
            throw new IllegalStateException("note new title already exist");
        existedNote.setTitle(title);
        existedNote.setContent(content);
        existedNote.setModifiedDate(new Date());
        noteRepo.save(existedNote);
        return "note modified";
    }

    public List<Note> getFolderNotes(Long folderId){
        if(folderId == null || folderRepo.findById(folderId).isEmpty())
            throw new NoSuchElementException("folder not found");
        return noteRepo.getNotesByFolderId(folderId);
    }

    public List<Note> getUserNotes(Long userId){
        if(userId == null)
            throw new NullPointerException("userId is null");
        return noteRepo.getNotesByFolderIn(folderRepo.getFoldersByUserId(userId));
    }

    public List<Note> getUserStarredNotes(Long userId){
        if(userId == null)
            throw new NullPointerException("userId is null");
        return noteRepo.getNotesByFolderInAndStarred(folderRepo.getFoldersByUserId(userId), true);
    }

    public Note moveNote(Long newFolderId, Long noteId){
        if(noteId == null || newFolderId == null || !folderRepo.existsFolderById(newFolderId) || !noteRepo.existsById(noteId))
            throw new NoSuchElementException("not found");
        Note note = noteRepo.findById(noteId).get();
        note.setFolder(folderRepo.findById(newFolderId).get());
        noteRepo.save(note);
        return note;
    }

    public String deleteNote(Long noteId){
        if(noteId == null || !noteRepo.existsById(noteId))
            throw new NoSuchElementException("note not found");
        noteRepo.deleteById(noteId);
        return "true";
    }

    public Note alterStar(Long noteId){
        if(noteId == null || !noteRepo.existsById(noteId))
            throw new NoSuchElementException("note not found");
        Note note = noteRepo.getReferenceById(noteId);
        note.setStarred(!note.isStarred());
        noteRepo.save(note);
        return note;
    }

    public Long getUserId(String email){
        return userRepo.getUserByEmail(email).get().getId();
    }

}
