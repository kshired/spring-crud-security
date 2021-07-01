package com.example.crud.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Posts> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comments> comments = new ArrayList<>();

    @Builder
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void addPost(Posts post){
        this.posts.add(post);
        post.setUser(this);
    }

    public void addComments(Comments comments){
        this.comments.add(comments);
        comments.setUser(this);
    }
}
