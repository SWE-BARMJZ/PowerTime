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
class OneTimeTaskRepoTest {
    @Autowired
    OneTimeTaskRepo oneTimeTaskRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @BeforeEach
    void setUp() {
        User user1 = new User("basel20ahmed@gmail.com", "Abdeebde_1023", "Basel", "Ahmed");
        User user2 = new User("meneim@gmail.com", "nswswA_9nee", "Meneim", "Hany");
        User user3 = new User("ramy@gmail.com", "dhebdbdQ_1488", "Ramy", "Ahmed");
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        categoryRepo.save(new Category("Hobbies", user1));
        categoryRepo.save(new Category("Assignments", user1));
        categoryRepo.save(new Category("Sports", user1));
        categoryRepo.save(new Category("Hobbies", user2));
        categoryRepo.save(new Category("Assignments", user2));
        categoryRepo.save(new Category("Sports", user2));
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
        User user = userRepo.getUserByEmail("basel20ahmed@gmail.com").get();
        Category category = categoryRepo.getCategoryByCategoryNameAndUser("Assignments", user).get();
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
        assertThat(oneTimeTasks).isEqualTo(List.of(oneTimeTask1, oneTimeTask2));

    }

    @Test
    void gettingOneTimeTasksWithToDoFlagEqualTrueInTheRightOrder() {
        User user = userRepo.getUserByEmail("basel20ahmed@gmail.com").get();
        Category category1 = categoryRepo.getCategoryByCategoryNameAndUser("Assignments", user).get();
        Category category2 = categoryRepo.getCategoryByCategoryNameAndUser("Hobbies", user).get();
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
        assertThat(oneTimeTasks.get(2)).isEqualTo(oneTimeTask2);
        assertThat(oneTimeTasks.get(3)).isEqualTo(oneTimeTask1);

    }

    @Test
    void getTheLatestCompletedTasks() {
        // given
        User user = userRepo.getUserByEmail("meneim@gmail.com").get();
        Category category1 = categoryRepo.getCategoryByCategoryNameAndUser("Assignments", user).get();
        Category category2 = categoryRepo.getCategoryByCategoryNameAndUser("Hobbies", user).get();

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
        assertThat(tasks).isEqualTo(List.of(task1, task4, task2));
    }

    @Test
    void getAllByCategory() {
        User user = userRepo.getUserByEmail("meneim@gmail.com").get();
        Category category1 = categoryRepo.getCategoryByCategoryNameAndUser("Assignments", user).get();
        Category category2 = categoryRepo.getCategoryByCategoryNameAndUser("Hobbies", user).get();

        OneTimeTask task1 = OneTimeTask.builder()
                .taskName("Assignment 1 Database")
                .category(category1)
                .creationDate(Date.valueOf("2020-12-04"))
                .dueDate(Date.valueOf("2022-12-14"))
                .completionDate(Date.valueOf("2022-12-14"))
                .user(user)
                .build();

        OneTimeTask task2 = OneTimeTask.builder()
                .taskName("Gym")
                .category(category2)
                .creationDate(Date.valueOf("2020-12-07"))
                .completionDate(Date.valueOf("2020-12-16"))
                .user(user)
                .build();

        OneTimeTask task3 = OneTimeTask.builder()
                .taskName("Assignments 3 AI")
                .category(category1)
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
        List<OneTimeTask> tasksOfCategory1 = oneTimeTaskRepo.getAllByCategory(category1).get();
        List<OneTimeTask> tasksOfCategory2 = oneTimeTaskRepo.getAllByCategory(category2).get();

        // then
        assertThat(tasksOfCategory1).isEqualTo(List.of(task1, task3));
        assertThat(tasksOfCategory2).isEqualTo(List.of(task2, task4));
    }

    @Test
    void markTaskAsDone() {
        Date creationDate = Date.valueOf("2022-07-08");
        User user = userRepo.getUserByEmail("meneim@gmail.com").get();
        Category category = categoryRepo.getCategoryByCategoryNameAndUser("Assignments", user).get();
        OneTimeTask task = OneTimeTask.builder()
                .taskName("Assignment 1 SWE")
                .category(category)
                .user(user)
                .creationDate(creationDate)
                .dueDate(Date.valueOf("2022-10-11"))
                .build();
        java.util.Date completionDate = Date.from(Instant.now());

        // when
        oneTimeTaskRepo.save(task);
        Long taskId = oneTimeTaskRepo.getByCreationDate(creationDate).get().getId();
        oneTimeTaskRepo.markTaskAsDone(taskId, completionDate);

        // then
        java.util.Date actualCompletionDate = oneTimeTaskRepo.findById(taskId).get().getCreationDate();
        assertThat(actualCompletionDate).isNotNull();
    }

    @Test
    void unMarkTaskAsDone() {
        Date creationDate = Date.valueOf("2022-07-08");
        User user = userRepo.getUserByEmail("meneim@gmail.com").get();
        Category category = categoryRepo.getCategoryByCategoryNameAndUser("Assignments", user).get();
        OneTimeTask task = OneTimeTask.builder()
                .taskName("Assignment 1 SWE")
                .category(category)
                .user(user)
                .creationDate(creationDate)
                .dueDate(Date.valueOf("2022-10-11"))
                .completionDate(Date.valueOf("2022-10-10"))
                .build();

        // when
        oneTimeTaskRepo.save(task);
        Long taskId = oneTimeTaskRepo.getByCreationDate(creationDate).get().getId();
        oneTimeTaskRepo.unMarkTaskAsDone(taskId);

        // then
        assertThat(oneTimeTaskRepo.findById(taskId).get().getCompletionDate()).isNull();
    }

    @Test
    void changeTodoFlagToTrue() {
        // given
        Date date = Date.valueOf("2022-07-08");
        User user = userRepo.getUserByEmail("meneim@gmail.com").get();
        Category category = categoryRepo.getCategoryByCategoryNameAndUser("Assignments", user).get();
        OneTimeTask task = OneTimeTask.builder()
                .taskName("Assignment 1 SWE")
                .category(category)
                .user(user)
                .creationDate(date)
                .build();

        // when
        oneTimeTaskRepo.save(task);
        oneTimeTaskRepo.changeTodoFlag(oneTimeTaskRepo.getByCreationDate(date).get().getId(), true);

        // then
        assertThat(oneTimeTaskRepo.getByCreationDate(date).get().isTodo()).isTrue();
    }

    @Test
    void getByCreationDate() {
        // given
        java.util.Date creationDate = Date.valueOf("2022-12-17");
        User user = userRepo.getUserByEmail("meneim@gmail.com").get();
        Category category = categoryRepo.getCategoryByCategoryNameAndUser("Sports", user).get();
        OneTimeTask task = OneTimeTask.builder()
                .taskName("Football Match")
                .category(category)
                .user(user)
                .creationDate(creationDate)
                .dueDate(Date.valueOf("2022-12-24"))
                .build();

        // when
        oneTimeTaskRepo.save(task);
        OneTimeTask actualTask = oneTimeTaskRepo.getByCreationDate(creationDate).get();

        // then
        assertThat(actualTask).isEqualTo(task);
    }
}