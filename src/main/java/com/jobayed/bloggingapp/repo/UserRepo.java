package com.jobayed.bloggingapp.repo;

import com.jobayed.bloggingapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
