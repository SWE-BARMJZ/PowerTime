package com.barmjz.productivityapp.Folder;

import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.AfterEach;
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
class ModifyFolderTest {

    @Autowired
    FolderRepo folderRepo;
    @Autowired
    UserRepo userRepo;

    FolderManager folderManager;
    User user1;
    Folder folder1;
    Folder folder2;

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

        userRepo.save(user1);
        Date date = new Date();
        folder1 = Folder.builder()
                .name("folder1")
                .user(user1)
                .CreatedDate(date)
                .modifiedDate(date)
                .build();
        folder2 = Folder.builder()
                .name("folder2")
                .user(user1)
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void modifyFolder(){
        folderRepo.save(folder1);
        Date lastModifiedDate = folder1.getModifiedDate();
        Date lastCreatedDate = folder1.getCreatedDate();
        folderManager.modifyFolder(folder1.getId(), "newFolder1");
        assertThat(folder1.getName()).isEqualTo("newFolder1");
        assertThat(folderRepo.getFoldersByUserId(user1.getId()).get(0)).isEqualTo(folder1);
        assertThat(lastModifiedDate).isBefore(folder1.getModifiedDate());
        assertThat(lastCreatedDate).isEqualTo(folder1.getCreatedDate());
    }

    @Test
    void modifyNonExistFolder(){
        folderRepo.save(folder1);
        assertThatThrownBy(() -> folderManager.modifyFolder(null,"newFolder"))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("folderId is null");
        assertThatThrownBy(() -> folderManager.modifyFolder(folder1.getId()+3,"newFolder"))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("folder not found");
    }

    @Test
    void modifyDuplicatedName(){
        folderRepo.save(folder1);
        folderRepo.save(folder2);
        assertThatThrownBy(() -> folderManager.modifyFolder(folder2.getId(),"folder1"))
                .hasMessageContaining("folder name already exists")
                .isInstanceOf(IllegalStateException.class);
    }

}