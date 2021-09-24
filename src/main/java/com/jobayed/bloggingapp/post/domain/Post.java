package com.jobayed.bloggingapp.post.domain;

import com.jobayed.bloggingapp.auth_user.domain.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String postTitle;
    private String postDetails;
}
