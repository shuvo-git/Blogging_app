package com.jobayed.bloggingapp.post.api;

import com.jobayed.bloggingapp.post.domain.Post;
import com.jobayed.bloggingapp.post.service.PostService;
import com.jobayed.bloggingapp.post.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostResource
{
    private final PostService postService;

    @GetMapping("/posts")
    public String getAllPosts(Model model)
    {
        model.addAttribute("posts","value of post");
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts",posts);
        return "index";
    }


}
