package com.barmjz.productivityapp.Note;

import com.barmjz.productivityapp.Folder.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepo extends JpaRepository<Note,Long> {
    List<Note> getNotesByFolderId(Long folderId);
    List<Note> getNotesByFolderIn(List<Folder> foldersId);
}
