package com.example.crud.controller;

import com.example.crud.domain.User;
import com.example.crud.dto.UserPasswordDto;
import com.example.crud.dto.UserRequestDto;
import com.example.crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/api/v1/user/sign-up")
    public Long signUp(@RequestBody UserRequestDto requestDto){
        return userService.join(requestDto);
    }

    @PostMapping("/api/v1/user/password")
    public Long changePassword(@RequestBody UserPasswordDto dto, @AuthenticationPrincipal UserDetails user){
        boolean matches = passwordEncoder.matches(dto.getCurPassword(), user.getPassword());
        if(!matches){
           throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        userService.changePassword(user.getUsername(),dto.getNewPassword());
        return 1L;
    }
}
