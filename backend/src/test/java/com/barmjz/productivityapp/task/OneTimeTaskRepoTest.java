package com.barmjz.productivityapp.todomindmap.repo;


import com.barmjz.productivityapp.todomindmap.category.Category;
import com.barmjz.productivityapp.todomindmap.category.CategoryRepo;
import com.barmjz.productivityapp.todomindmap.repos.OneTimeTaskRepo;
import com.barmjz.productivityapp.todomindmap.task.OneTimeTask;
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
class OneTimeTaskRepoTest {
    @Autowired
    OneTimeTaskRepo oneTimeTaskRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @BeforeEach
    void setUp() {
        userRepo.save(new User("basel20ahmed@gmail.com", "Abdeebde_1023", "Basel", "Ahmed"));
        userRepo.save(new User("meneim@gmail.com", "nswswA_9nee", "Meneim", "Hany"));
        userRepo.save(new User("ramy@gmail.com", "dhebdbdQ_1488", "Ramy", "Ahmed"));
        categoryRepo.save(new Category("Hobbies"));
        categoryRepo.save(new Category("Assignments"));
    }

    @AfterEach
    void tearDown() {
        oneTimeTaskRepo.deleteAll();
        categoryRepo.deleteAll();
        userRepo.deleteAll();
    }

    @Test
    void gettingRightTasksGivenUserId() {
        // given
        Category category = categoryRepo.getCategoryByCategoryName("Assignments").get();
        User user = userRepo.getUserByEmail("basel20ahmed@gmail.com").get();
        OneTimeTask oneTimeTask1 = OneTimeTask.builder()
                .taskName("Assignment 1 Database")
                .category(category)
                .creationDate(Date.valueOf("2020-12-16"))
                .dueDate(Date.valueOf("2022-12-30"))
                .user(user)
                .build();

        OneTimeTask oneTimeTask2 = OneTimeTask.builder()
                .taskName("Assignment 2 Algorithms")
                .taskDesc("Use Huffman algorithm to compress files efficiently")
                .category(category)
                .creationDate(Date.valueOf("2020-12-16"))
                .dueDate(Date.valueOf("2022-12-30"))
                .user(user)
                .build();

        // when
        oneTimeTaskRepo.save(oneTimeTask1);
        oneTimeTaskRepo.save(oneTimeTask2);
        List<OneTimeTask> oneTimeTasks = oneTimeTaskRepo.getAllByUserId(user.getId()).get();

        // then
        assertThat(List.of(oneTimeTask1, oneTimeTask2)).isEqualTo(oneTimeTasks);

    }

    @Test
    void gettingOneTimeTasksWithToDoFlagEqualTrueInTheRightOrder() {
        Category category1 = categoryRepo.getCategoryByCategoryName("Assignments").get();
        Category category2 = categoryRepo.getCategoryByCategoryName("Hobbies").get();

        User user = userRepo.getUserByEmail("basel20ahmed@gmail.com").get();
        OneTimeTask oneTimeTask1 = OneTimeTask.builder()
                .taskName("Assignment 1 Database")
                .category(category1)
                .creationDate(Date.valueOf("2020-12-16"))
                .dueDate(Date.valueOf("2022-12-30"))
                .user(user)
                .todo(true)
                .build();

        OneTimeTask oneTimeTask2 = OneTimeTask.builder()
                .taskName("Assignment 2 Algorithms")
                .taskDesc("Use Huffman algorithm to compress files efficiently")
                .category(category1)
                .creationDate(Date.valueOf("2020-12-16"))
                .dueDate(Date.valueOf("2022-12-19"))
                .user(user)
                .todo(true)
                .build();

        OneTimeTask oneTimeTask3 = OneTimeTask.builder()
                .taskName("Gym")
                .category(category2)
                .creationDate(Date.valueOf("2020-12-16"))
                .user(user)
                .todo(true)
                .build();
        OneTimeTask oneTimeTask4 = OneTimeTask.builder()
                .taskName("Preparing food")
                .category(category2)
                .creationDate(Date.valueOf("2020-12-15"))
                .user(user)
                .todo(true)
                .build();
        OneTimeTask oneTimeTask5 = OneTimeTask.builder()
                .taskName("Assignment 3 AI")
                .taskDesc("Implement value iteration")
                .category(category1)
                .creationDate(Date.valueOf("2020-12-16"))
                .dueDate(Date.valueOf("2022-12-29"))
                .user(user)
                .build();

        // when
        oneTimeTaskRepo.saveAll(List.of(oneTimeTask1, oneTimeTask2, oneTimeTask3, oneTimeTask4, oneTimeTask5));
        List<OneTimeTask> oneTimeTasks = oneTimeTaskRepo.getAllByUserIdAndTodoEqualsOrderByDueDate(user.getId(), true).get();

        // then
        assertThat(oneTimeTask2).isEqualTo(oneTimeTasks.get(2));
        assertThat(oneTimeTask1).isEqualTo(oneTimeTasks.get(3));

    }

    @Test
    void getTheLatestCompletedTasks() {
        // given
        Category category1 = categoryRepo.getCategoryByCategoryName("Assignments").get();
        Category category2 = categoryRepo.getCategoryByCategoryName("Hobbies").get();
        User user = userRepo.getUserByEmail("meneim@gmail.com").get();

        OneTimeTask task1 = OneTimeTask.builder()
                .taskName("Assignment 1 Database")
                .category(category1)
                .creationDate(Date.valueOf("2020-12-04"))
                .dueDate(Date.valueOf("2022-12-14"))
                .completionDate(Date.valueOf("2022-12-14"))
                .user(user)
                .build();

        OneTimeTask task2 = OneTimeTask.builder()
                .taskName("Assignment 1 AI")
                .category(category1)
                .creationDate(Date.valueOf("2020-12-07"))
                .completionDate(Date.valueOf("2020-12-16"))
                .user(user)
                .build();

        OneTimeTask task3 = OneTimeTask.builder()
                .taskName("Gym")
                .category(category2)
                .creationDate(Date.valueOf("2020-12-04"))
                .dueDate(Date.valueOf("2022-12-29"))
                .user(user)
                .build();

        OneTimeTask task4 = OneTimeTask.builder()
                .taskName("Football match")
                .category(category2)
                .creationDate(Date.valueOf("2020-12-04"))
                .dueDate(Date.valueOf("2022-12-10"))
                .completionDate(Date.valueOf("2022-12-09"))
                .user(user)
                .build();

        // when
        oneTimeTaskRepo.saveAll(List.of(task1, task2, task3, task4));
        List<OneTimeTask> tasks = oneTimeTaskRepo.getTop10ByUserIdAndCompletionDateNotNullOrderByCompletionDateDesc(user.getId()).get();

        // then
        assertThat(List.of(task1, task4, task2)).isEqualTo(tasks);
    }


}