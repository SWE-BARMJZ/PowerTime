package com.barmjz.productivityapp.Folder;

import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FolderManager {
    /*
        two built in folders(starred, main)
        main will get all user notes
        starred will get starred user notes
    */

    private final FolderRepo folderRepo;
    private final UserRepo userRepo;

    public Folder createFolder(Long userId, String folderName){
        if(userId == null)
            throw new NullPointerException("userId is null");
        Optional<User> user = userRepo.findById(userId);
        if(!user.isPresent())
            throw new NoSuchElementException("user not found");
        Folder newFolder = Folder.builder()
                .name(folderName)
                .user(user.get())
                .CreatedDate(new Date())
                .modifiedDate(new Date())
                .build();
        if(folderRepo.existsFolderByNameAndUser_Id(folderName,user.get().getId()))
            throw new IllegalStateException("folder name already exists");
        folderRepo.save(newFolder);
        return newFolder;
    }

    public Folder modifyFolder(Long folderId, String folderName){
        // modified in DB but return to view changes(name, Date modified) in front
        if(folderId == null)
            throw new NullPointerException("folderId is null");
        if(!folderRepo.existsFolderById(folderId))
            throw new IllegalStateException("folder not found");
        Folder folder = folderRepo.getReferenceById(folderId);
        if(folderRepo.existsFolderByNameAndUser_Id(folderName,folder.getUser().getId()))
            throw new IllegalStateException("folder name already exists");
        folder.setName(folderName);
        folder.setModifiedDate(new Date());
        return folder;
    }

    public List<Folder> getUserFolders(Long userId){
        if(userId == null || !userRepo.existsById(userId))
            throw new NoSuchElementException("user not found");
        // if user has no folders return null or empty  list of folders
        return folderRepo.getFoldersByUserId(userId);

    }

    public boolean deleteFolder(Long folderId){
        if(!folderRepo.existsFolderById(folderId))
            throw new NoSuchElementException("folder not found");
        // check if note are deleted by cascade or not
        folderRepo.deleteById(folderId);
        return true;
    }
}
