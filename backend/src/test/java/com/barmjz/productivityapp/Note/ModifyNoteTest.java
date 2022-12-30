package com.barmjz.productivityapp.Note;

import com.barmjz.productivityapp.Folder.Folder;
import com.barmjz.productivityapp.Folder.FolderRepo;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ModifyNoteTest {

    @Autowired
    UserRepo userRepo;
    @Autowired
    FolderRepo folderRepo;
    @Autowired NoteRepo noteRepo;
    NoteManager noteManager;
    User user1;
    Folder folder;
    Note note1;
    Note note2;
    Date date;

    @BeforeEach
    void setUp() {
        noteManager = new NoteManager(noteRepo, folderRepo);
        folderRepo.deleteAll();
        noteRepo.deleteAll();
        date = new Date();
        user1 = User.builder()
                .email("user1@gmail.com")
                .password("pass")
                .firstName("userFirst")
                .lastName("userLast")
                .build();
        userRepo.save(user1);
        folder = Folder.builder()
                .name("folder")
                .user(user1)
                .CreatedDate(date)
                .modifiedDate(date)
                .build();
    }

    @Test
    void modifyNote(){
        folderRepo.save(folder);
        Date date = new Date();
        note1 = noteManager.createNote(folder.getId(), "note");
        note2 = Note.builder()
                .id(note1.getId())
                .title("note")
                .content("note 1 content")
                .folder(folder)
                .createdDate(date)
                .modifiedDate(date)
                .fontSize(8)
                .build();
        noteManager.modifyNote(note2, folder.getId());
        assertThat(noteRepo.findById(note1.getId()).get().getContent()).isEqualTo("note 1 content");
        assertThat(noteRepo.findById(note1.getId()).get().getFontSize()).isEqualTo(8);
//        assertThat(noteRepo.findById(note1.getId()).get()).isEqualTo(note2);
    }

    @Test
    void invalidModifiedNote(){
        assertThatThrownBy(() -> noteManager.modifyNote(null, folder.getId()))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("note is null");
    }

    @Test
    void modifyNonExistedNote(){
        folderRepo.save(folder);
        Date date = new Date();
        note1 = noteManager.createNote(folder.getId(), "note");
        note2 = Note.builder()
                .id(note1.getId()+1)
                .title("note")
                .content("note 1 content")
                .folder(folder)
                .createdDate(date)
                .modifiedDate(date)
                .fontSize(8)
                .build();
        assertThatThrownBy(() -> noteManager.modifyNote(note2,folder.getId()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("note not found");
    }
}