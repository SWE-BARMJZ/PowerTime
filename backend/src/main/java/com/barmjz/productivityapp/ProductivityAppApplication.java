package com.barmjz.productivityapp;

import com.barmjz.productivityapp.User.User;
import com.barmjz.productivityapp.User.UserRepoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProductivityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductivityAppApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(UserRepoService userRepoService, PasswordEncoder encoder){
//		return args -> {
//			userRepoService.saveUser(new User("user2", encoder.encode("password") ,"he","he", "ROLE_ADMIN"));
//		};
//	}

}
