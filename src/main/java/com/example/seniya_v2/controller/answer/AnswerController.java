package com.example.seniya_v2.controller.answer;

import com.example.seniya_v2.common.constants.ApiMappingPattern;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.answer.request.AnswerCreateRequestDto;
import com.example.seniya_v2.dto.answer.response.AnswerResponseDto;
import com.example.seniya_v2.service.AnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.ANSWER_API)
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<ResponseDto<AnswerResponseDto>> createAnswer(
            @PathVariable Long id,
            @AuthenticationPrincipal String username,
            @Valid @RequestBody AnswerCreateRequestDto dto
    ) {
        ResponseDto<AnswerResponseDto> response = answerService.createAnswer(id, username, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
