package com.barmjz.productivityapp;


import com.barmjz.productivityapp.config.RsaKeyProperties;
import com.barmjz.productivityapp.todo_task_category.category.Category;
import com.barmjz.productivityapp.todo_task_category.category.CategoryRepo;
import com.barmjz.productivityapp.todo_task_category.task.OneTimeTask;
import com.barmjz.productivityapp.todo_task_category.task.OneTimeTaskRepo;
import com.barmjz.productivityapp.todo_task_category.task.RepeatedTask;
import com.barmjz.productivityapp.todo_task_category.task.RepeatedTaskRepo;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.List;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class ProductivityAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductivityAppApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner mappingDemo(UserRepo userRepo, CategoryRepo categoryRepo, OneTimeTaskRepo oneTimeTaskRepo,
//										 RepeatedTaskRepo repeatedTaskRepo) {
//		return args -> {
//			oneTimeTaskRepo.deleteAll();
//			repeatedTaskRepo.deleteAll();
//			categoryRepo.deleteAll();
//			userRepo.deleteAll();
//
//			User user = new User("basel20ahmed@gmail.com", "Abdeebde_1023", "Basel", "Ahmed");
//			User user1 = new User("meneim@gmail.com", "Abdee_1023", "Meneim", "Hany");
//			userRepo.save(user);
//			userRepo.save(user1);
//			Category category1 = new Category("Hobby", user);
//			Category category2 = new Category("Assignments", user);
//			RepeatedTask repeatedTask = RepeatedTask.builder()
//					.taskName("Gym")
//					.creationDate(Date.valueOf("1990-01-02"))
//					.sunday(true)
//					.user(user)
//					.category(category1)
//					.build();
//
//			OneTimeTask oneTimeTask = OneTimeTask.builder()
//					.taskName("Assignment 1")
//					.creationDate(Date.valueOf("1990-06-04"))
//					.user(user)
//					.category(category2)
//					.build();
//			categoryRepo.save(category1);
//			categoryRepo.save(category2);
////			Category c3 = new Category(category1.getCategoryByCategory_nameAndUserUser());
////			categoryRepo.save(c3);
//			oneTimeTaskRepo.save(oneTimeTask);
//			repeatedTaskRepo.save(repeatedTask);
//			OneTimeTask task = oneTimeTaskRepo.getByCreationDate(oneTimeTask.getCreationDate()).get();
//			long id = task.getId();
//			System.out.println("----"+id);
//			task.setTaskName("******");
//			oneTimeTaskRepo.save(task);
//			oneTimeTaskRepo.deleteById(id);
//			oneTimeTaskRepo.save(task);

//			userRepo.deleteById(2L);
//			oneTimeTaskRepo.deleteAll();
//			categoryRepo.deleteById(1L);


//			User user1 = new User("basel20ahmed@gmail.com", "Abdeebde_1023", "Basel", "Ahmed");
//			User user2 = new User("meneim@gmail.com", "nswswA_9nee", "Meneim", "Hany");
//			User user3 = new User("ramy@gmail.com", "dhebdbdQ_1488", "Ramy", "Ahmed");
//			userRepo.save(user1);
//			userRepo.save(user2);
//			userRepo.save(user3);
//			User user1p = userRepo.getUserByEmail("basel20ahmed@gmail.com").get();
//			User user2p = userRepo.getUserByEmail("meneim@gmail.com").get();
//			categoryRepo.save(new Category("Hobbies", user1p));
//			categoryRepo.save(new Category("Assignments", user1p));
//			categoryRepo.save(new Category("Sports", user1p));
//			categoryRepo.save(new Category("Hobbies", user2p));
//			categoryRepo.save(new Category("Assignments", user2p));
//			categoryRepo.save(new Category("Sports", user2p));
//
//			User user = userRepo.getUserByEmail("basel20ahmed@gmail.com").get();
//			Category category = categoryRepo.getCategoryByCategoryNameAndUser("Assignments", user).get();
//			OneTimeTask oneTimeTask1 = OneTimeTask.builder()
//					.taskName("Assignment 1 Database")
//					.category(category)
//					.creationDate(Date.valueOf("2020-12-16"))
//					.dueDate(Date.valueOf("2022-12-30"))
//					.user(user)
//					.build();
//
//			OneTimeTask oneTimeTask2 = OneTimeTask.builder()
//					.taskName("Assignment 2 Algorithms")
//					.taskDesc("Use Huffman algorithm to compress files efficiently")
//					.category(category)
//					.creationDate(Date.valueOf("2020-12-16"))
//					.dueDate(Date.valueOf("2022-12-30"))
//					.user(user)
//					.build();
//
//			// when
//			oneTimeTaskRepo.save(oneTimeTask1);
//			oneTimeTaskRepo.save(oneTimeTask2);
//			List<OneTimeTask> oneTimeTasks = oneTimeTaskRepo.getAllByUserId(user.getId()).get();

//		};
}
