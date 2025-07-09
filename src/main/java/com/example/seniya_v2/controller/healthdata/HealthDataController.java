package com.example.seniya_v2.controller.healthdata;

import com.example.seniya_v2.common.constants.ApiMappingPattern;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.healthdata.request.HealthDataRequestDto;
import com.example.seniya_v2.dto.healthdata.request.HealthDataUpdRequestDto;
import com.example.seniya_v2.dto.healthdata.response.HealthDataResponseDto;
import com.example.seniya_v2.service.HealthDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.HEALTH_DATA_API)
public class HealthDataController {

    private final HealthDataService healthDataService;

    @PostMapping
    public ResponseEntity<ResponseDto<HealthDataResponseDto>> createHealthData(
            @AuthenticationPrincipal String username,
            @Valid @RequestBody HealthDataRequestDto dto
    ) {
        ResponseDto<HealthDataResponseDto> response = healthDataService.createHealthData(username, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto<HealthDataResponseDto>> updateHealthData(
            @AuthenticationPrincipal String username,
            @Valid @RequestBody HealthDataUpdRequestDto dto
    ) {
        ResponseDto<HealthDataResponseDto> response = healthDataService.updateHealthData(username, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseDto<HealthDataResponseDto>> getMyHealthData(@AuthenticationPrincipal String username) {
        ResponseDto<HealthDataResponseDto> response = healthDataService.getMyHealthData(username);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
