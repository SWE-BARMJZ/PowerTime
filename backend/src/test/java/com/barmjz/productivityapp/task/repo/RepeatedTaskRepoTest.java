package com.barmjz.productivityapp.task.repo;

import com.barmjz.productivityapp.category.Category;
import com.barmjz.productivityapp.category.CategoryRepo;
import com.barmjz.productivityapp.task.RepeatedTaskRepo;
import com.barmjz.productivityapp.task.RepeatedTask;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class RepeatedTaskRepoTest {

    @Autowired
    UserRepo userRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    RepeatedTaskRepo repeatedTaskRepo;

    @BeforeEach
    void setUp() {
        userRepo.save(new User("basel20ahmed@gmail.com", "Abdeebde_1023", "Basel", "Ahmed"));
        userRepo.save(new User("meneim@gmail.com", "nswswA_9nee", "Meneim", "Hany"));
        userRepo.save(new User("ramy@gmail.com", "dhebdbdQ_1488", "Ramy", "Ahmed"));
        categoryRepo.save(new Category("Hobbies"));
        categoryRepo.save(new Category("Assignments"));
        categoryRepo.save(new Category("Language Courses"));

    }

    @AfterEach
    void tearDown() {
        repeatedTaskRepo.deleteAll();
        categoryRepo.deleteAll();
        userRepo.deleteAll();
    }

    @Test
    void gettingRightTasksGivenUserId() {
        // given
        Category category = categoryRepo.getCategoryByCategoryName("Assignments").get();
        User user = userRepo.getUserByEmail("basel20ahmed@gmail.com").get();
        RepeatedTask task1 = RepeatedTask.builder()
                .taskName("Assignment 1 Database")
                .category(category)
                .creationDate(Date.valueOf("2020-12-16"))
                .user(user)
                .build();

        RepeatedTask task2 = RepeatedTask.builder()
                .taskName("Assignment 2 Algorithms")
                .taskDesc("Use Huffman algorithm to compress files efficiently")
                .category(category)
                .creationDate(Date.valueOf("2020-12-16"))
                .user(user)
                .build();

        // when
        repeatedTaskRepo.save(task1);
        repeatedTaskRepo.save(task2);
        List<RepeatedTask> tasks = repeatedTaskRepo.getAllByUserId(user.getId()).get();

        // then
        assertThat(List.of(task1, task2)).isEqualTo(tasks);

    }

    @Test
    void getRepeatedTaskOnSunday() {
        // given
        Category category1 = categoryRepo.getCategoryByCategoryName("Language Courses").get();
        Category category2 = categoryRepo.getCategoryByCategoryName("Hobbies").get();

        User user = userRepo.getUserByEmail("basel20ahmed@gmail.com").get();
        RepeatedTask task1 = RepeatedTask.builder()
                .taskName("German Course")
                .category(category1)
                .creationDate(Date.valueOf("2022-12-16"))
                .sunday(true)
                .user(user)
                .build();

        RepeatedTask task2 = RepeatedTask.builder()
                .taskName("Gym")
                .category(category2)
                .creationDate(Date.valueOf("2022-12-18"))
                .sunday(true)
                .user(user)
                .build();

        RepeatedTask task3 = RepeatedTask.builder()
                .taskName("English Course")
                .category(category1)
                .creationDate(Date.valueOf("2022-12-08"))
                .monday(true)
                .user(user)
                .build();

        // when
        repeatedTaskRepo.saveAll(List.of(task1, task2, task3));
        List<RepeatedTask> tasks = repeatedTaskRepo.getAllByUserIdAndSundayEqualsAndLastRemovalDateNot(user.getId(), true, Date.valueOf("2022-12-17")).get();

        // then
        assertThat(List.of(task1, task2)).isEqualTo(tasks);

    }

}