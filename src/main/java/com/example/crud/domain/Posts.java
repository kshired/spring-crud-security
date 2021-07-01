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
public class Posts extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name="post_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comments> comments = new ArrayList<>();

    public void setUser(User user) {
        this.user = user;
    }

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void addComments(Comments comments){
        this.comments.add(comments);
        comments.setPosts(this);
    }
}
