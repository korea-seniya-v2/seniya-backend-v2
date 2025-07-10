package com.example.seniya_v2.service;

import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.healthdata.request.HealthDataRequestDto;
import com.example.seniya_v2.dto.healthdata.request.HealthDataUpdRequestDto;
import com.example.seniya_v2.dto.healthdata.response.HealthDataResponseDto;
import jakarta.validation.Valid;

public interface HealthDataService {
    ResponseDto<HealthDataResponseDto> createHealthData(String username, @Valid HealthDataRequestDto dto);

    ResponseDto<HealthDataResponseDto> updateHealthData(String username, @Valid HealthDataUpdRequestDto dto);

    ResponseDto<HealthDataResponseDto> getMyHealthData(String username);
}
