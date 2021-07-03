package com.example.crud.util;

import com.example.crud.domain.Posts;
import com.example.crud.domain.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

public class PostSpec {
    public static Specification<Posts> equalAuthorName(String author){
        return (root, query, criteriaBuilder) -> {
            if(!StringUtils.hasText(author)){
                return null;
            }
            Join<Posts, User> t = root.join("user",JoinType.INNER);
            return criteriaBuilder.equal(t.get("username"),author);
        };
    }
    public static Specification<Posts> likePostsContent(String content) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("content"), "%" + content + "%");
    }

    public static Specification<Posts> likePostsTitle(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

}
