package com.example.crud.repository;

import com.example.crud.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
    @Query("SELECT c FROM Comments c JOIN FETCH c.user u INNER JOIN c.post p WHERE p.id = ?1")
    List<Comments> findCommentsByPostId(Long postId);
}
