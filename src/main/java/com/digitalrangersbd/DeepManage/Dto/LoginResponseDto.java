package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Entity.User;

public class LoginResponseDto {
    private String token;
    private UserResponseDto user;

    public LoginResponseDto(String token, UserResponseDto user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserResponseDto getUser() {
        return user;
    }
}
