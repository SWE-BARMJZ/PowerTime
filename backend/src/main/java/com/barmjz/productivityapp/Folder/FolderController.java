package com.barmjz.productivityapp.Folder;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/folder")
public class FolderController {

    private FolderManager folderManager;

    @PostMapping("/create")
    public ResponseEntity<Folder> createFolder(@RequestBody String folderName) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
        String email = "Ali14@gmail.com";
        Long userId = folderManager.getUserId(email);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(folderManager.createFolder(userId, folderName));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<Folder> modifyFolder(@RequestParam("folderId") Long folderId, @RequestParam("folderName") String folderName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(folderManager.modifyFolder(folderId, folderName));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Folder>> getUserFolders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long userId = folderManager.getUserId(email);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(folderManager.getUserFolders(userId));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFolder(@RequestParam("folderId") Long folderId) {
        try{
            folderManager.deleteFolder(folderId);
            return ResponseEntity.status(HttpStatus.OK).body("folder deleted successfully");
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("folder not found");
        }
    }
}