package com.example.seniya_v2.service;

import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.pass.response.PassResponseDto;

import java.util.List;

public interface PassService {
    ResponseDto<List<PassResponseDto>> getMyValidPasses(String username);
}
