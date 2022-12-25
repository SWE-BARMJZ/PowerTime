package com.barmjz.productivityapp.Note;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/note")
public class NoteController {

    private NoteManager noteManager;

    @PostMapping("/create")
    public ResponseEntity<Note> createNote(@RequestParam("folderId") Long folderId, @RequestParam("noteTitle") String noteTitle) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(noteManager.createNote(folderId, noteTitle));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<Note> modifyNote(@RequestParam("folderId") Long folderId, @RequestBody String modifiedNote) {
        try {
            System.out.println(modifiedNote);
//            return ResponseEntity.status(HttpStatus.OK).body(noteManager.modifyNote(modifiedNote, folderId));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/getFolderNotes")
    public ResponseEntity<List<Note>> getFolderNotes(@RequestParam("folderId") Long folderId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(noteManager.getFolderNotes(folderId));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/getUserNotes")
    public ResponseEntity<List<Note>> getUserNotes(@RequestParam("userId") Long userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(noteManager.getUserNotes(userId));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/getStarredNotes")
    public ResponseEntity<List<Note>> getUserStarredNotes(@RequestParam("userId") Long userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(noteManager.getUserStarredNotes(userId));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/moveNote")
    public ResponseEntity<Note> moveNote(@RequestParam("newFolderId") Long newFolderId, @RequestParam("noteId") Long noteId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(noteManager.moveNote(newFolderId, noteId));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deleteNote")
    public ResponseEntity<String> deleteNote(@RequestParam("noteId") Long noteId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(noteManager.deleteNote(noteId));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/alterStar")
    public ResponseEntity<String> alterStar(@RequestParam("noteId") Long noteId) {
        try {
            noteManager.alterStar(noteId);
            return ResponseEntity.status(HttpStatus.OK).body("Done");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fuck");

        }
    }
}
