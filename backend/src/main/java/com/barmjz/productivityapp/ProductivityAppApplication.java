package com.barmjz.productivityapp;

import com.barmjz.productivityapp.User.User;
import com.barmjz.productivityapp.User.UserRepoManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductivityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductivityAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepoManager userRepoManager){
		return args -> {
			userRepoManager.saveUser(new User("he2", "he" ,"he","he"));
		};
	}

}
