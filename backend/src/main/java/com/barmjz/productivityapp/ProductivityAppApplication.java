package com.barmjz.productivityapp;


import com.barmjz.productivityapp.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class ProductivityAppApplication {

	public static void main(String[] args) {SpringApplication.run(ProductivityAppApplication.class, args);}

}
