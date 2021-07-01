package com.example.crud.dto;

import com.example.crud.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {
    private String username;
    private String password;

    @Builder
    public UserRequestDto(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
