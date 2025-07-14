package com.example.seniya_v2.service;

import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.user.request.UserSignInRequestDto;
import com.example.seniya_v2.dto.user.request.UserSignUpRequestDto;
import com.example.seniya_v2.dto.user.response.UserSignInResponseDto;
import com.example.seniya_v2.dto.user.response.UserSignUpResponseDto;
import jakarta.validation.Valid;

public interface AuthService {
    ResponseDto<UserSignUpResponseDto> signup(@Valid UserSignUpRequestDto dto);

    ResponseDto<UserSignInResponseDto> login(@Valid UserSignInRequestDto dto);

    ResponseDto<?> logout(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
