package com.jobayed.bloggingapp.post.repo;

import com.jobayed.bloggingapp.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long>
{
    //public List<Post> findAllByOrderByIdDesc();
}
