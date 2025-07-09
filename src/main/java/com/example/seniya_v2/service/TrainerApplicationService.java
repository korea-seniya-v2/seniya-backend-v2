package com.example.seniya_v2.service;

import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.trainer.request.TrainerApplicationStatusRequestDto;
import com.example.seniya_v2.dto.trainer.response.TrainerApplicationDetailResponseDto;
import com.example.seniya_v2.dto.trainer.response.TrainerApplicationResponseDto;
import com.example.seniya_v2.dto.trainer.response.TrainerApplicationStatusResponseDto;

import java.util.List;

public interface TrainerApplicationService {
    ResponseDto<TrainerApplicationStatusResponseDto> applyTrainer(String username);

    ResponseDto<TrainerApplicationStatusResponseDto> getMyApplication(String username);

    ResponseDto<List<TrainerApplicationResponseDto>> getAllApplication();

    ResponseDto<TrainerApplicationDetailResponseDto> getApplicationById(Long id);

    ResponseDto<TrainerApplicationStatusResponseDto> updateStatus(Long id, TrainerApplicationStatusRequestDto dto);

}
