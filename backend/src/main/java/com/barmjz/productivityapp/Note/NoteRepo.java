package com.barmjz.productivityapp.Note;

import com.barmjz.productivityapp.Folder.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note,Long> {
    boolean existsByTitleAndFolder_Id(String noteTitle, Long folderId);
    List<Note> getNotesByFolderId(Long folderId);
    List<Note> getNotesByFolderIn(List<Folder> foldersId);
    Note findByTitleAndFolder_Id(String noteTitle, Long folderId);
    List<Note> getNotesByFolderInAndIsStarred(List<Folder> foldersId, boolean isStarred);
}
