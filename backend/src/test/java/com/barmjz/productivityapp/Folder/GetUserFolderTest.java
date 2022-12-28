package com.barmjz.productivityapp.Folder;

import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.AfterEach;
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
class GetUserFolderTest {

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
    void getExistedUserFolders(){
        userRepo.save(user1);
        folderRepo.save(folder1);
        folderRepo.save(folder2);
        List<Folder> expectedUserFolders = new ArrayList<>();
        expectedUserFolders.add(folder1);
        expectedUserFolders.add(folder2);
        assertThat(folderManager.getUserFolders(user1.getId())).isEqualTo(expectedUserFolders);
    }

    @Test
    void getNonExistedUserFolders(){
        userRepo.save(user1);
        folderRepo.save(folder1);
        folderRepo.save(folder2);
        assertThatThrownBy(() -> folderManager.getUserFolders(user1.getId()+3))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("user not found");

    }
}