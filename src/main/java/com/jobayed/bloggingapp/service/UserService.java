package com.jobayed.bloggingapp.service;

import com.jobayed.bloggingapp.domain.Role;
import com.jobayed.bloggingapp.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void assignRole(String username, String rolename);
    User getUser(String username);
    List<User> getUsers();
}
