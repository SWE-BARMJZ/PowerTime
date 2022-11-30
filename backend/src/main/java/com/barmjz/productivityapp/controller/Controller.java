package com.barmjz.productivityapp.controller;

import com.barmjz.productivityapp.User.User;
import com.barmjz.productivityapp.User.UserRepoManager;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class Controller {
    private final UserRepoManager userRepoManager;

    @GetMapping
    public Iterable<User> findAll(){
        return userRepoManager.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") User user){
        return user;
    }
}
