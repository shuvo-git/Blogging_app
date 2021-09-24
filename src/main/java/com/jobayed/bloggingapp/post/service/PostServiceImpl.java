package com.jobayed.bloggingapp.post.service;

import com.jobayed.bloggingapp.post.domain.Post;
import com.jobayed.bloggingapp.post.repo.PostRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class PostServiceImpl implements PostService
{
    private final PostRepo postRepo;

    private String getCurrentDateTime()
    {
        Date date = new Date();
        String strDateFormat = "d MM, Y hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        return formattedDate;
    }

    @Override
    public List<Post> getPosts()
    {
        return postRepo.findAll();
    }

    @Override
    public Post create(Post post) {
        Post p;
        log.info("Saving Post at {}",this.getCurrentDateTime());
        p = postRepo.save(post);
        log.info("Saved new Post {} at {} successfully.",p.getPostTitle(),this.getCurrentDateTime());
        return p;
    }

    @Override
    public Post get(Long id)
    {
        Post p;
        log.info("Retrieving Post at {}",this.getCurrentDateTime());
        p = postRepo.getById(id);
        log.info("Retrieved Post {} at {} successfully.",p.getPostTitle(),this.getCurrentDateTime());
        return p;
    }

    @Override
    public void delete(Long id) {
        Post p;
        log.info("Retrieving Post at {}",this.getCurrentDateTime());
        p = postRepo.getById(id);
        log.info("Retrieved Post {} at {} successfully.",p.getPostTitle(),this.getCurrentDateTime());
        postRepo.delete(p);
        log.info("Deleted Post at {} successfully.",this.getCurrentDateTime());

    }
}
