package com.barmjz.productivityapp.Folder;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FolderRepo extends JpaRepository<Folder,Long> {
    List<Folder> getFoldersByUserId(Long userId);
    boolean existsFolderById(Long folderId);
    boolean existsFolderByNameAndUser_Id(String name, Long user_id);

    @Transactional
    @Modifying
    @Query(value = "update Folder set name = ?1 where id = ?2", nativeQuery = true)
    boolean updateFolderById(String folderName, Long folderId);
}
