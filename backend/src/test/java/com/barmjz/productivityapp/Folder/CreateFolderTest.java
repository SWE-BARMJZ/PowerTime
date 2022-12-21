package com.barmjz.productivityapp.Folder;

import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CreateFolderTest {
    @Autowired
    FolderRepo folderRepo;
    @Autowired
    UserRepo userRepo;

    FolderManager folderManager;

    User user1;
    User user2;

    @BeforeEach
    void setUp() {
        folderManager = new FolderManager(folderRepo,userRepo);
        userRepo.deleteAll();
        folderRepo.deleteAll();
        user1 = User.builder()
                .email("user1@gmail.com")
                .password("pass")
                .firstName("user1First")
                .lastName("user1Last")
                .build();
        user2 = User.builder()
                .email("user2@gmail.com")
                .password("pass")
                .firstName("user2First")
                .lastName("user2Last")
                .build();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createFolder(){
        userRepo.save(user1);
        Folder newFolder = folderManager.createFolder(user1.getId(),"folder1");
        assertThat(folderRepo.findById(newFolder.getId()).get()).isEqualTo(newFolder);
        assertThat(folderRepo.existsFolderById(newFolder.getId())).isTrue();
        assertThat(folderRepo.existsFolderById(newFolder.getId()+1)).isFalse();
        assertThat(folderRepo.getFoldersByUserId(user1.getId()).get(0)).isEqualTo(newFolder);
        assertThat(folderRepo.existsFolderByNameAndUser_Id("folder1",user1.getId())).isTrue();
    }

    @Test
    void createFolders(){
        userRepo.save(user1);
        Folder newFolder1 = folderManager.createFolder(user1.getId(),"folder1");
        Folder newFolder2 = folderManager.createFolder(user1.getId(),"folder2");
        List<Folder> expectedFolders = new ArrayList<>();
        expectedFolders.add(newFolder1);
        expectedFolders.add(newFolder2);
        assertThat(folderRepo.getFoldersByUserId(user1.getId())).isEqualTo(expectedFolders);
    }

    @Test
    void createFolderWithNonExistedUser(){
        assertThatThrownBy(() -> folderManager.createFolder(20L,"folder1"))
                .hasMessageContaining("user not found")
                .isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(() -> folderManager.createFolder(user1.getId(),"folder1"))
                .hasMessageContaining("userId is null")
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void addDuplicatedFolders(){
        userRepo.save(user1);
        userRepo.save(user2);
        Folder folder1 = folderManager.createFolder(user1.getId(),"folder");
        assertThatThrownBy(() -> folderManager.createFolder(user1.getId(),"folder"))
                .hasMessageContaining("folder name already exists")
                .isInstanceOf(IllegalStateException.class);
        assertDoesNotThrow(() -> folderManager.createFolder(user2.getId(),"folder"));
    }

}