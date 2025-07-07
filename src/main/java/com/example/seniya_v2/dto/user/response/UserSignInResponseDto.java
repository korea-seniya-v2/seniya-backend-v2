package com.example.seniya_v2.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignInResponseDto {
    private String token;
    private UserResponseDto user;
    private int exprTime;
    private int roleId;
}
