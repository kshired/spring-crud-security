package com.example.crud.controller;

import com.example.crud.dto.UserRequestDto;
import com.example.crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/v1/user/sign-up")
    public Long signUp(@RequestBody UserRequestDto requestDto){
        return userService.join(requestDto);
    }
}
