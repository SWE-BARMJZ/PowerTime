package com.barmjz.productivityapp.Folder;

import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class FolderDBTest {
    @Autowired  FolderRepo folderRepo;
    @Autowired  UserRepo userRepo;
    User user1;
    User user2;
    Folder folder1;
    Folder folder2;

    @BeforeEach
    void setUp() {
        folderRepo.deleteAll();
        userRepo.deleteAll();
        user1 = User.builder()
                .email("user1@gmail.com")
                .password("pass")
                .firstName("userFirst")
                .lastName("userLast")
                .build();
        user2 = User.builder()
                .email("user2@gmail.com")
                .password("pass")
                .firstName("userFirst")
                .lastName("userLast")
                .build();
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
    void addFolderToExistedUser(){
        userRepo.save(user1);
        folderRepo.save(folder1);
        assertThat(folderRepo.findById(folder1.getId()).isPresent()).isTrue();
    }

    @Test
    void addManyFoldersToExistedUser(){
        userRepo.save(user1);
        folderRepo.save(folder1);
        folderRepo.save(folder2);
        assertThat(folderRepo.findById(folder1.getId()).get().getUser())
                .isEqualTo(folderRepo.findById(folder2.getId()).get().getUser());
    }

    @Test
    void getNonExistedFolder(){
        userRepo.save(user1);
        assertThat(folderRepo.findById(6L).isPresent()).isFalse();
    }

    @Test
    void getUserFolders(){
        userRepo.save(user1);
        folderRepo.save(folder1);
        folderRepo.save(folder2);
        List<Folder> expectedUser1Folders = new ArrayList<>();
        expectedUser1Folders.add(folder1);
        expectedUser1Folders.add(folder2);
        List<Folder> user1Folders = folderRepo.getFoldersByUserId(user1.getId());
        assertThat(expectedUser1Folders).isEqualTo(user1Folders);
    }

    @Test
    void checkUserFolderRelation(){
        userRepo.save(user1);
        folderRepo.save(folder1);
        assertThat(folderRepo.findById(folder1.getId()).get().getUser().getEmail())
                .isEqualTo(user1.getEmail());
    }

}