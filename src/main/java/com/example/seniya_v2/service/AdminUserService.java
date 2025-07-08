package com.example.seniya_v2.service;

import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.admin.user.response.GetAllUserResponseDto;
import com.example.seniya_v2.dto.admin.user.response.GetUserDetailResponseDto;

import java.util.List;

public interface AdminUserService {
    ResponseDto<List<GetAllUserResponseDto>> getAllUser();

    ResponseDto<GetUserDetailResponseDto> getUserDetail(long userId);
}
