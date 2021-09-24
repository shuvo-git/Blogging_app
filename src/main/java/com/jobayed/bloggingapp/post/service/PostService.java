package com.jobayed.bloggingapp.post.service;

import com.jobayed.bloggingapp.post.domain.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService
{
    List<Post> getPosts();
    Post create(Post post);
    Post get(Long id);
    //Post update(Long id,  Post post);
    void delete(Long id);

}
