package com.example.crud.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserPasswordDto {
    String curPassword;
    String newPassword;

    @Builder
    public UserPasswordDto(String curPassword, String newPassword) {
        this.curPassword = curPassword;
        this.newPassword = newPassword;
    }
}
