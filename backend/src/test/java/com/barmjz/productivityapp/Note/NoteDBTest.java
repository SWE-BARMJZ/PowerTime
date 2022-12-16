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
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.not;

@DataJpaTest
class NoteDBTest {

    @Autowired NoteRepo noteRepo;
    @Autowired FolderRepo folderRepo;
    @Autowired UserRepo userRepo;

    User user1;
    Folder folder1;
    Folder folder2;

    @BeforeEach
    void setUp() {
        user1 = User.builder()
                .email("user1@gmail.com")
                .password("pass")
                .firstName("userFirst")
                .lastName("userLast")
                .build();
        userRepo.save(user1);

        folder1 = Folder.builder()
                .name("folder1")
                .user(user1)
                .build();

        folder2 = Folder.builder()
                .name("folder2")
                .user(user1)
                .build();
    }

    @Test
    void addNoteToFolder(){
        folderRepo.save(folder1);
        Note note1 = Note.builder()
                .title("note1")
                .folder(folder1)
                .build();
        noteRepo.save(note1);
        assertThat(noteRepo.findById(note1.getId()).isPresent()).isTrue();
        assertThat(noteRepo.findById(note1.getId()).get().getFolder()).isEqualTo(folder1);
        assertThat(noteRepo.findById(note1.getId()).get().isStarred()).isFalse();
    }

    @Test
    void getFoldersNotes(){
        folderRepo.save(folder1);
        Note note1 = Note.builder()
                .title("note1")
                .folder(folder1)
                .build();
        Note note2 = Note.builder()
                .title("note2")
                .folder(folder1)
                .build();
        noteRepo.save(note1);
        noteRepo.save(note2);
        List<Note> expectedNotes = new ArrayList<>();
        expectedNotes.add(note1);
        expectedNotes.add(note2);
        assertThat(noteRepo.getNotesByFolderId(folder1.getId())).isEqualTo(expectedNotes);
        assertThat(noteRepo.getNotesByFolderId(folder2.getId())).isEqualTo(new ArrayList<>());
    }

    @Test
    void getUserNotes(){
        folderRepo.save(folder1);
        folderRepo.save(folder2);
        Note note1 = Note.builder()
                .title("note1")
                .folder(folder1)
                .build();
        Note note2 = Note.builder()
                .title("note2")
                .folder(folder2)
                .build();
        noteRepo.save(note1);
        noteRepo.save(note2);
        List<Note> expectedNotes = new ArrayList<>();
        expectedNotes.add(note1);
        expectedNotes.add(note2);
        List<Folder> userFolders = folderRepo.getFoldersByUserId(user1.getId());
        List<Note> userNotes = noteRepo.getNotesByFolderIn(userFolders);
        assertThat(expectedNotes).isEqualTo(userNotes);
    }

    @Test
    void addTwoNotesWithSameTitle(){
        Note note1 = Note.builder()
                .title("note")
                .folder(folder1)
                .build();
        Note note2 = Note.builder()
                .title("note")
                .folder(folder1)
                .build();
        noteRepo.save(note1);
        noteRepo.save(note2);

    }




}