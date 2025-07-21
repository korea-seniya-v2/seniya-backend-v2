package com.example.seniya_v2.service;

import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.answer.request.AnswerCreateRequestDto;
import com.example.seniya_v2.dto.answer.response.AnswerResponseDto;
import jakarta.validation.Valid;

public interface AnswerService {
    ResponseDto<AnswerResponseDto> createAnswer(Long id, String username, @Valid AnswerCreateRequestDto dto);
}
