package com.barmjz.productivityapp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepoManager {

    @Autowired
    UserRepo userRepo;

    public void saveUser(User user){
        userRepo.save(user);
    }


}