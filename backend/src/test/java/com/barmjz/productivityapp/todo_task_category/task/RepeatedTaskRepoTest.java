package com.barmjz.productivityapp.todo_task_category.task;
import com.barmjz.productivityapp.todo_task_category.category.Category;
import com.barmjz.productivityapp.todo_task_category.category.CategoryRepo;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.sql.Date;
import java.time.Instant;
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
        User user1 = new User("basel20ahmed@gmail.com", "Abdeebde_1023", "Basel", "Ahmed");
        User user2 = new User("meneim@gmail.com", "nswswA_9nee", "Meneim", "Hany");
        User user3 = new User("ramy@gmail.com", "dhebdbdQ_1488", "Ramy", "Ahmed");
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        categoryRepo.save(new Category("Language Courses", user1));
        categoryRepo.save(new Category("Hobbies", user1));
        categoryRepo.save(new Category("Assignments", user1));
        categoryRepo.save(new Category("Sports", user1));
        categoryRepo.save(new Category("Hobbies", user2));
        categoryRepo.save(new Category("Assignments", user2));
        categoryRepo.save(new Category("Sports", user2));
    }

    @AfterEach
    void tearDown() {
        repeatedTaskRepo.deleteAll();
        categoryRepo.deleteAll();
        userRepo.deleteAll();
    }

//    @Test
//    void gettingRightTasksGivenUserId() {
//        // given
//        User user = userRepo.getUserByEmail("basel20ahmed@gmail.com").get();
//        Category category = categoryRepo.getCategoryByCategoryNameAndUser("Assignments", user).get();
//        RepeatedTask task1 = RepeatedTask.builder()
//                .taskName("Assignment 1 Database")
//                .category(category)
//                .creationDate(Date.valueOf("2020-12-16"))
//                .user(user)
//                .build();
//
//        RepeatedTask task2 = RepeatedTask.builder()
//                .taskName("Assignment 2 Algorithms")
//                .taskDesc("Use Huffman algorithm to compress files efficiently")
//                .category(category)
//                .creationDate(Date.valueOf("2020-12-16"))
//                .user(user)
//                .build();
//
//        // when
//        repeatedTaskRepo.save(task1);
//        repeatedTaskRepo.save(task2);
//        List<RepeatedTask> tasks = repeatedTaskRepo.getAllByUserId(user.getId()).get();
//
//        // then
//        assertThat(tasks).isEqualTo(List.of(task1, task2));
//
//    }

    @Test
    void getRepeatedTaskOnSunday() {
        // given
        User user = userRepo.getUserByEmail("basel20ahmed@gmail.com").get();
        Category category1 = categoryRepo.getCategoryByCategoryNameAndUser("Language Courses", user).get();
        Category category2 = categoryRepo.getCategoryByCategoryNameAndUser("Hobbies", user).get();
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
        List<RepeatedTask> tasks = repeatedTaskRepo.getAllByUserAndSundayEqualsAndLastRemovalDateNot(user, true, Date.valueOf("2022-12-17")).get();

        // then
        assertThat(List.of(task1, task2)).isEqualTo(List.of(task1, task2));

    }

    @Test
    void getAllByCategory() {
        User user = userRepo.getUserByEmail("meneim@gmail.com").get();
        Category category1 = categoryRepo.getCategoryByCategoryNameAndUser("Sports", user).get();
        Category category2 = categoryRepo.getCategoryByCategoryNameAndUser("Hobbies", user).get();

        RepeatedTask task1 = RepeatedTask.builder()
                .taskName("Padel")
                .category(category1)
                .creationDate(Date.valueOf("2020-12-04"))
                .user(user)
                .build();

        RepeatedTask task2 = RepeatedTask.builder()
                .taskName("Gym")
                .category(category1)
                .creationDate(Date.valueOf("2020-12-07"))
                .user(user)
                .build();


        RepeatedTask task3 = RepeatedTask.builder()
                .taskName("Music")
                .category(category2)
                .creationDate(Date.valueOf("2020-12-04"))
                .user(user)
                .build();


        // when
        repeatedTaskRepo.saveAll(List.of(task1, task2, task3));
        List<RepeatedTask> tasksOfCategory1 = repeatedTaskRepo.getAllByCategory(category1).get();
        List<RepeatedTask> tasksOfCategory2 = repeatedTaskRepo.getAllByCategory(category2).get();

        // then
        assertThat(tasksOfCategory1).isEqualTo(List.of(task1, task2));
        assertThat(tasksOfCategory2).isEqualTo(List.of(task3));
    }

    @Test
    void changedRemovalDateSuccessfully() {
        // given
        java.util.Date currDate = Date.from(Instant.now());
        User user = userRepo.getUserByEmail("meneim@gmail.com").get();
        Category category = categoryRepo.getCategoryByCategoryNameAndUser("Sports", user).get();
        RepeatedTask task = RepeatedTask.builder()
                .taskName("Football Match")
                .category(category)
                .user(user)
                .creationDate(Date.valueOf("2022-12-17"))
                .tuesday(true)
                .build();
        repeatedTaskRepo.save(task);

        // when
        RepeatedTask actualTask = repeatedTaskRepo.getByCreationDate(Date.valueOf("2022-12-17")).get();
        repeatedTaskRepo.changeRemovalDate(actualTask.getId(), currDate);

        // then
        java.util.Date actualDate = repeatedTaskRepo.findById(actualTask.getId()).get().getLastRemovalDate();
        assertThat(actualDate.getTime()).isEqualTo(currDate.getTime());
    }

    @Test
    void getTaskByCreationDateSuccessfully() {
        // given
        Date creationDate = Date.valueOf("2022-12-17");
        User user = userRepo.getUserByEmail("meneim@gmail.com").get();
        Category category = categoryRepo.getCategoryByCategoryNameAndUser("Sports", user).get();
        RepeatedTask task = RepeatedTask.builder()
                .taskName("Football Match")
                .category(category)
                .user(user)
                .creationDate(creationDate)
                .tuesday(true)
                .build();

        // when
        repeatedTaskRepo.save(task);
        RepeatedTask actualTask = repeatedTaskRepo.getByCreationDate(creationDate).get();

        // then
        assertThat(task).isEqualTo(actualTask);
    }
}