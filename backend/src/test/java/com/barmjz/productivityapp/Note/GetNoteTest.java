package com.barmjz.productivityapp.Note;

import com.barmjz.productivityapp.Folder.Folder;
import com.barmjz.productivityapp.Folder.FolderRepo;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class GetNoteTest {

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
        noteManager = new NoteManager(noteRepo, folderRepo);
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
    void getInvalidEmptyFolderNotes(){
        assertThatThrownBy(() -> noteManager.getFolderNotes(null))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("folder not found");
        assertThatThrownBy(() -> noteManager.getFolderNotes(folder.getId()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("folder not found");
        folderRepo.save(folder);
        List<Note> folderNotes = noteManager.getFolderNotes(folder.getId());
        assertThat(folderNotes).isEqualTo(new ArrayList<>());
    }

    @Test
    void getFolderNotes(){
        folderRepo.save(folder);
        note1 = noteManager.createNote(folder.getId(),"note1");
        note2 = noteManager.createNote(folder.getId(),"note2");
        List<Note> folderNotes = new ArrayList<>();
        folderNotes.add(note1);
        folderNotes.add(note2);
        assertThat(folderNotes).isEqualTo(noteManager.getFolderNotes(folder.getId()));
    }

    @Test
    void getUserNotes(){
        folderRepo.save(folder);
        folderRepo.save(folder2);
        note1 = noteManager.createNote(folder.getId(),"note1");
        note2 = noteManager.createNote(folder2.getId(),"note2");
        List<Note> folderNotes = new ArrayList<>();
        folderNotes.add(note1);
        folderNotes.add(note2);
        assertThat(folderNotes).isEqualTo(noteManager.getUserNotes(folder.getUser().getId()));
    }

    @Test
    void getStarredNotes(){
        folderRepo.save(folder);
        folderRepo.save(folder2);
        note1 = noteManager.createNote(folder.getId(),"note1");
        note2 = noteManager.createNote(folder2.getId(),"note2");
        note2.setContent("content2");
        noteManager.alterStar(note2.getId());
        noteManager.modifyNote(note2);
        assertThat(note2.isStarred()).isTrue();
        assertThat(noteRepo.findById(note2.getId()).get().getContent()).isEqualTo("content2");
        List<Note> starredNotes = new ArrayList<>();
        starredNotes.add(note2);
        assertThat(starredNotes).isEqualTo(noteManager.getUserStarredNotes(folder.getUser().getId()));

    }
}
