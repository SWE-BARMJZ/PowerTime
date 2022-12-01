package com.barmjz.productivityapp.user;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest     // test but db affected
//@DataJpaTest       // test only flush data after test so db not affected

class UserLoginTest {

    @Autowired
    UserRepo userRepo;

    @Test
    public void saveUser(){
        User user = new User(
                "user1@gmail.com",
                "1234",
                "user1Name",
                "user1LastName",
                "ROLE_USER"
        );
        try {
            userRepo.save(user);
        }catch (Exception e){
            System.out.println("invalid, email duplication");
        }
        User user2 = new User(
                "user2@gmail.com",
                "4321",
                "user2Name",
                "user2LastName",
                "ROLE_USER"
        );
        try {
            userRepo.save(user2);
        }catch (Exception e){
            System.out.println("invalid, email duplication");
        }

    }

    @Test
    public void getUserByEmail(){
        User user2 = new User(
                "user2@gmail.com",
                "4321",
                "user2Name",
                "user2LastName",
                "ROLE_USER"
        );

        User retrievedUser2 = userRepo.getUsersByEmail("user2@gmail.com").get();
        user2.setId(retrievedUser2.getId());
        assert userRepo.getUsersByEmail("user2@gmail.com").toString().equals(user2.toString());
        User retrievedUser1 = userRepo.getUsersByEmail("user1@gmail.com").get();
        user2.setId(retrievedUser1.getId());
        assert !userRepo.getUsersByEmail("user1@gmail.com").toString().equals(user2.toString());

    }
}