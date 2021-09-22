package com.jobayed.bloggingapp;

import com.jobayed.bloggingapp.auth_user.domain.Role;
import com.jobayed.bloggingapp.auth_user.domain.User;
import com.jobayed.bloggingapp.auth_user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class BloggingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingAppApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args->{
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_BLOGGER"));

			userService.saveUser(new User(null,"Jobayed Ullah","jobayed","1234",new ArrayList<>()));
			userService.saveUser(new User(null,"Asrafun Naher","asrafun","1234",new ArrayList<>()));

			userService.assignRole("jobayed","ROLE_ADMIN");
			userService.assignRole("asrafun","ROLE_BLOGGER");
		};
	}
}
