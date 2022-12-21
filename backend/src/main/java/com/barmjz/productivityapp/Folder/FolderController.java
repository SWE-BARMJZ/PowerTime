package com.barmjz.productivityapp.Folder;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/folder")
public class FolderController {

    private FolderManager folderManager;

    @PostMapping("/create")
    public ResponseEntity<Folder> createFolder(@RequestBody String folderName) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails user = (UserDetails) currentUser.getPrincipal();
//        System.out.println(user.getUsername());
//        System.out.println(currentUser.getCredentials());

        try {
            Long userId = folderManager.getUserId(currentUser.getName());
            return ResponseEntity.status(HttpStatus.OK).body(folderManager.createFolder(userId, folderName));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<String> modifyFolder(@RequestParam("folderId") Long folderId, @RequestParam("folderName") String folderName) {
        try {
            folderManager.modifyFolder(folderId, folderName);
            return ResponseEntity.status(HttpStatus.OK).body("Done");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fuck");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Folder>> getUserFolders() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        try {
            Long userId = folderManager.getUserId(currentUser.getName());
            return ResponseEntity.status(HttpStatus.OK).body(folderManager.getUserFolders(userId));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFolder(@RequestParam("folderId") Long folderId) {
        try{
            System.out.println(folderId+1);
            folderManager.deleteFolder(folderId);
            return ResponseEntity.status(HttpStatus.OK).body("folder deleted successfully");
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("folder not found");
        }
    }
}