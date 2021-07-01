package com.example.crud.controller;

import com.example.crud.config.MyUserDetails;
import com.example.crud.domain.Comments;
import com.example.crud.dto.CommentsRequestDto;
import com.example.crud.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentsApiController {
    private final CommentsService commentsService;

    @PostMapping("/api/v1/posts/{id}/comments")
    public Long save(@PathVariable("id") Long id, @RequestBody CommentsRequestDto commentsRequestDto, @AuthenticationPrincipal MyUserDetails user){
        return commentsService.save(commentsRequestDto,id, user.getUsername());
    }

    @DeleteMapping("/api/v1/comments/{id}")
    public Long delete(@PathVariable("id") Long id, @AuthenticationPrincipal MyUserDetails user){
        return commentsService.delete(id,user.getUsername());
    }

}
