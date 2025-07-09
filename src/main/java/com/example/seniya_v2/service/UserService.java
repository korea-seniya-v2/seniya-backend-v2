package com.example.seniya_v2.service;

import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.user.request.MyInfoUpdateRequestDto;
import com.example.seniya_v2.dto.user.response.GetMyInfoResponseDto;
import jakarta.validation.Valid;

public interface UserService {
    ResponseDto<GetMyInfoResponseDto> getUserInfo(String username);

    ResponseDto<GetMyInfoResponseDto> updateUserInfo(String username, @Valid MyInfoUpdateRequestDto dto);

    ResponseDto<?> deleteUser(String username);
}
