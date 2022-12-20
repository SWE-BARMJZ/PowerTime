package com.barmjz.productivityapp.Folder;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/folder")
public class FolderController {

    private FolderManager folderManager;

    @PostMapping("/create")
    public ResponseEntity<Folder> createFolder(@RequestParam("folderName") String folderName, @RequestParam("email") String email) {
        try {
            Long userId = folderManager.getUserId(email);
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
    public ResponseEntity<List<Folder>> getUserFolders(@RequestParam("email") String email) {
        try {
            Long userId = folderManager.getUserId(email);
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