package com.example.crud.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comments extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Posts post;

    public void setUser(User user) {
        this.user = user;
    }

    public void setPosts(Posts post) {
        this.post = post;
    }

    @Builder
    public Comments(String content){
        this.content = content;
    }
}
