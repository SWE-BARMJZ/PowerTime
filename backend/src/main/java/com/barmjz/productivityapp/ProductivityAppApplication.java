package com.barmjz.productivityapp;


import com.barmjz.productivityapp.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

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
//			userRepo.deleteAll();
//			User user = new User("basel20ahmed@gmail.com", "Abdeebde_1023", "Basel", "Ahmed");
//			userRepo.save(user);
//			Category category1 = new Category("Hobby");
//			Category category2 = new Category("Assignments");
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
//			oneTimeTaskRepo.deleteAll();
//			repeatedTaskRepo.deleteAll();
//			categoryRepo.deleteAll();
//			categoryRepo.save(category1);
//			categoryRepo.save(category2);
//			oneTimeTaskRepo.save(oneTimeTask);
//			repeatedTaskRepo.save(repeatedTask);
//		};
//	}
}
