package com.barmjz.productivityapp.Note;

import com.barmjz.productivityapp.Folder.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note,Long> {
    List<Note> getNotesByFolderId(Long folderId);
    List<Note> getNotesByFolderIn(List<Folder> foldersId);
}
