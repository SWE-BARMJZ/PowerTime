package com.barmjz.productivityapp.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class DBConnectionTest {

    @Autowired
    UserRepo userRepo;

    @AfterEach
    void tearDown(){
        userRepo.deleteAll();
    }

    @Test
    void itChecksIfUserIsAddedInDBSuccessfully(){

        // Given
        String email = "dummy@gmail.com";
        User user = new User(email,
                "password",
                "Jack",
                "Grealish");
        // When
        userRepo.save(user);
        boolean userExists = userRepo.getUserByEmail(email).isPresent();
        // Then
        assertThat(userExists).isTrue();

    }
    @Test
    void itChecksIfUserDoesntExist(){

        // Given
        String email = "dummy@gmail.com";
        // When
        boolean userExists = userRepo.getUserByEmail(email).isPresent();
        //Then
        assertThat(userExists).isFalse();

    }

    @Test
    void itChecksDeletionFunctionality(){

        // Given
        String email = "dummy@gmail.com";
        User user = new User(email,
                "password",
                "Jack",
                "Grealish");
        // When
        userRepo.save(user);
        Optional<User> userDB = userRepo.getUserByEmail(email);
        boolean userExists = userDB.isPresent();
        userRepo.deleteById(userDB.get().getId());
        boolean userExists2 = userRepo.getUserByEmail(email).isPresent();
        // Then
        assertThat(userExists).isTrue();
        assertThat(userExists2).isFalse();

    }

    @Test
    void itChecksIfUserIsValidated(){

        // Given
        String email = "dummy@gmail.com";
        User user = new User(email,
                "password",
                "Jack",
                "Grealish");
        // When
        userRepo.save(user);
        Optional<User> userDB = userRepo.getUserByEmail(email);
        boolean userExists = userDB.isPresent();
        userRepo.deleteById(userDB.get().getId());
        boolean userExists2 = userRepo.getUserByEmail(email).isPresent();
        // Then
        assertThat(userExists).isTrue();
        assertThat(userExists2).isFalse();

    }
}
