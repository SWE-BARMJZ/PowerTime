package com.barmjz.productivityapp.Note;

import com.barmjz.productivityapp.Folder.Folder;
import com.barmjz.productivityapp.Folder.FolderRepo;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CreateNoteTest {
    @Autowired UserRepo userRepo;
    @Autowired FolderRepo folderRepo;
    @Autowired NoteRepo noteRepo;
    NoteManager noteManager;
    User user;
    Folder folder;
    Note note1;
    Note note2;
    Date date;

    @BeforeEach
    void setUp() {
        noteManager = new NoteManager(noteRepo, folderRepo, userRepo);
        folderRepo.deleteAll();
        noteRepo.deleteAll();
        date = new Date();
        user = User.builder()
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
    }

    @Test
    void createNote(){
        folderRepo.save(folder);
        note1 = noteManager.createNote(folder.getId(), "first note title");
        assertThat(note1.getTitle()).isEqualTo("first note title");
        assertThat(noteRepo.existsByTitleAndFolder_Id("first note title",folder.getId())).isTrue();
        assertThat(noteRepo.findByTitleAndFolder_Id("first note title",folder.getId()))
                .isEqualTo(noteRepo.findById(note1.getId()).get());
    }

    @Test
    void createNoteWithInvalidFolder(){
        assertThatThrownBy(() -> noteManager.createNote(null, "first note title"))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("folderId is null");
        folderRepo.save(folder);
        assertThatThrownBy(() -> noteManager.createNote(folder.getId()+3, "first note title"))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("folder not found");
    }

    @Test
    void createInvalidNote(){
        folderRepo.save(folder);
        note1 = noteManager.createNote(folder.getId(), "note");
        assertThatThrownBy(() -> noteManager.createNote(folder.getId(),"note"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("note title already exist");
    }

}