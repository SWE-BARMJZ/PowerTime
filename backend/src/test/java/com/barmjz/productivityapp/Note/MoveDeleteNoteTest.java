package com.barmjz.productivityapp.Note;

import com.barmjz.productivityapp.Folder.Folder;
import com.barmjz.productivityapp.Folder.FolderManager;
import com.barmjz.productivityapp.Folder.FolderRepo;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MoveDeleteNoteTest {

    @Autowired FolderRepo folderRepo;
    @Autowired NoteRepo noteRepo;
    @Autowired UserRepo userRepo;
    NoteManager noteManager;
    Folder folder;
    Folder folder2;
    Note note1;
    Note note2;
    Date date;

    @BeforeEach
    void setUp() {
        noteManager = new NoteManager(noteRepo, folderRepo, userRepo);
        folderRepo.deleteAll();
        noteRepo.deleteAll();
        date = new Date();
        User user = User.builder()
                .email("user1@gmail.com")
                .password("pass")
                .firstName("userFirst")
                .lastName("userLast")
                .build();
        userRepo.save(user);
        folder = Folder.builder()
                .name("folder")
                .user(user)
                .CreatedDate(date)
                .modifiedDate(date)
                .build();
        folder2 = Folder.builder()
                .name("folder2")
                .user(user)
                .CreatedDate(date)
                .modifiedDate(date)
                .build();
    }

    @Test
    void DeleteFolderCascadeNote(){
        folderRepo.save(folder);
        FolderManager folderManager = new FolderManager(folderRepo,userRepo);
        note1 = noteManager.createNote(folder.getId(), "note");
        folderManager.deleteFolder(folder.getId());
        assertThat(folderRepo.findById(folder.getId()).isPresent()).isFalse();
    }

//    @Test
//    void deleteNote(){
//        folderRepo.save(folder);
//        note1 = noteManager.createNote(folder.getId(),"note");
//        List<Note> expectedNotes = new ArrayList<>();
//        expectedNotes.add(note1);
//        assertThat(noteManager.getFolderNotes(folder.getId())).isEqualTo(expectedNotes);
//        noteManager.deleteNote(note1.getId());
//        assertThat(noteManager.getFolderNotes(folder.getId())).isEqualTo(new ArrayList<>());
//    }
}