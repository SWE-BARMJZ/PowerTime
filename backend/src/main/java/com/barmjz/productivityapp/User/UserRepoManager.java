package com.barmjz.productivityapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepoManager {

    @Autowired
    UserRepo userRepo;

    public void saveUser(User user){
        userRepo.save(user);
    }

    public Optional<User> getUserByEmail(String email){
        return userRepo.getUsersByEmail(email);
    }


}