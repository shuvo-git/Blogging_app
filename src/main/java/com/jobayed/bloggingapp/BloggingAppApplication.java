package com.jobayed.bloggingapp;

import com.jobayed.bloggingapp.auth_user.domain.Role;
import com.jobayed.bloggingapp.auth_user.domain.User;
import com.jobayed.bloggingapp.auth_user.service.UserService;
import com.jobayed.bloggingapp.post.domain.Post;
import com.jobayed.bloggingapp.post.service.PostService;
import com.jobayed.bloggingapp.post.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
			userService.saveUser(new User(null,"Altaf Mahmood","altaf",  "1234",new ArrayList<>()));
			userService.saveUser(new User(null,"Jasim Uddin","jasim",  "1234",new ArrayList<>()));

			userService.assignRole("jobayed","ROLE_ADMIN");
			userService.assignRole("altaf",  "ROLE_BLOGGER");
			userService.assignRole("jasim",  "ROLE_BLOGGER");


		};
	}

//	@Bean
//	CommandLineRunner run(PostService postService){
//		return args->{
//			postService.create(new Post(null,3L,"Post Title 1","this is the temp post details 1"));
//			postService.create(new Post(null,3L,"Post Title 2","this is the temp post details 2"));
//		};
//	}
}
