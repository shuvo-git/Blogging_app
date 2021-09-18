package com.jobayed.bloggingapp.repo;

import com.jobayed.bloggingapp.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
