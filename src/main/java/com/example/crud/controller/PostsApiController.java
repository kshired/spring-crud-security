package com.example.crud.controller;

import com.example.crud.config.MyUserDetails;
import com.example.crud.dto.PostsResponseDto;
import com.example.crud.dto.PostsSaveRequestDto;
import com.example.crud.dto.PostsUpdateRequestDto;
import com.example.crud.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto, @AuthenticationPrincipal MyUserDetails user){
        return postsService.save(requestDto,user.getUsername());
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody PostsUpdateRequestDto requestDto, @AuthenticationPrincipal MyUserDetails user){
        return postsService.update(id, requestDto, user.getUsername());
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return new PostsResponseDto(postsService.findById(id));
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long deleteById(@PathVariable Long id, @AuthenticationPrincipal MyUserDetails user){
        postsService.deleteById(id, user.getUsername());
        return id;
    }
}
