package com.barmjz.productivityapp.Note;

import com.barmjz.productivityapp.Folder.Folder;
import com.barmjz.productivityapp.Folder.FolderRepo;
import jakarta.transaction.Transactional;
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
                .isStarred(false)
                .folder(folder.get())
                .createdDate(date)
                .modifiedDate(date)
                .build();
        if(noteRepo.existsByTitleAndFolder_Id(noteTitle,folder.get().getId()))
            throw new IllegalStateException("note title already exist");
        noteRepo.save(newNote);
        return newNote;
    }

    public Note modifyNote(Note modifiedNote){
        // check update whole note in DB
        if(modifiedNote == null || modifiedNote.getId() == null)
            throw new NullPointerException("note is null");
        if(!noteRepo.existsById(modifiedNote.getId()))
            throw new NoSuchElementException("note not found");
        Note existedNote = noteRepo.getReferenceById(modifiedNote.getId());
        // need more efficient way
        existedNote.setContent(modifiedNote.getContent());
        existedNote.setModifiedDate(new Date());
        existedNote.setStarred(modifiedNote.isStarred());
        existedNote.setColor(modifiedNote.getColor());
        existedNote.setFontSize(modifiedNote.getFontSize());
        return modifiedNote;
    }

    public List<Note> getFolderNotes(Long folderId){
        if(folderId == null || !folderRepo.findById(folderId).isPresent())
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
        return noteRepo.getNotesByFolderInAndIsStarred(folderRepo.getFoldersByUserId(userId), true);
    }

    public boolean moveNote(Long newFolderId, Long noteId){
        if(noteId == null || newFolderId == null || !folderRepo.existsFolderById(newFolderId) || !noteRepo.existsById(noteId))
            throw new NoSuchElementException("not found");
        Note note = noteRepo.getReferenceById(noteId);
        note.setFolder(folderRepo.getReferenceById(newFolderId));
        return true;
    }

    public boolean deleteNote(Long noteId){
        if(noteId == null || !noteRepo.existsById(noteId))
            throw new NoSuchElementException("note not found");
        noteRepo.deleteById(noteId);
        return true;
    }

    public boolean alterStar(Long noteId){
        // just alter flag in front no need to return whole note
        if(noteId == null || !noteRepo.existsById(noteId))
            throw new NoSuchElementException("note not found");
        Note note = noteRepo.getReferenceById(noteId);
        note.setStarred(!note.isStarred());
        return note.isStarred();
    }

}
