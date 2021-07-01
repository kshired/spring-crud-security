package com.example.crud.controller;

import com.example.crud.config.MyUserDetails;
import com.example.crud.domain.Comments;
import com.example.crud.dto.CommentsRequestDto;
import com.example.crud.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentsApiController {
    private final CommentsService commentsService;

    @PostMapping("/api/v1/posts/{id}/comments")
    public Long save(@PathVariable("id") Long id, @RequestBody CommentsRequestDto commentsRequestDto, @AuthenticationPrincipal MyUserDetails user){
        return commentsService.save(commentsRequestDto,id, user.getUsername());
    }
}
