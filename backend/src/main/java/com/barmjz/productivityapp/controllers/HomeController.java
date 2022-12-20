package com.barmjz.productivityapp.controllers;
import com.barmjz.productivityapp.todo_task_category.category.Category;
import com.barmjz.productivityapp.todo_task_category.task.OneTimeTask;
import com.barmjz.productivityapp.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping()
public class HomeController {

    @GetMapping("/home")
    public OneTimeTask home(){
        User user = new User("basel20ahmed@gmail.com", "Abdeebde_1023", "Basel", "Ahmed");
        Category category = new Category("Assignments", user);
        OneTimeTask oneTimeTask = OneTimeTask.builder()
                .taskName("Assignment 1 Database")
                .category(category)
                .creationDate(Date.valueOf("2020-12-16"))
                .dueDate(Date.valueOf("2022-12-30"))
                .user(user)
                .build();

        return oneTimeTask;
    }

}
