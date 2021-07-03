package com.example.crud.repository;

import com.example.crud.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long>, JpaSpecificationExecutor<Posts> {
    List<Posts> findAllByOrderByIdDesc();
}
