package com.barmjz.productivityapp.Note;

import com.barmjz.productivityapp.Folder.FolderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteManager {
    @Autowired
    NoteRepo noteRepo;
    @Autowired
    FolderRepo folderRepo;

    public Note createNote(Long folderId, String noteTitle){
        return null;
    }

    public Note modifyNote(Note note){
        return null;

    }

    public List<Note> getFolderNotes(Long folderId){
        return null;

    }

    public boolean moveNote(Long newFolderId, Long noteId){
        // just change folderId in front no need to return whole note
        return false;
    }

    public boolean deleteNote(Long noteId){
        return false;
    }

    public boolean alterStar(Long NoteId){
        // just alter flag in front no need to return whole note
        return false;
    }

    public List<Note> getUserNotes(Long userId){
        return null;
    }

    public List<Note> getUserStarredNotes(Long userId){
        return null;
    }

}
