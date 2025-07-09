package com.example.seniya_v2.controller.trainer;

import com.example.seniya_v2.common.constants.ApiMappingPattern;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.trainer.request.TrainerApplicationStatusRequestDto;
import com.example.seniya_v2.dto.trainer.response.TrainerApplicationDetailResponseDto;
import com.example.seniya_v2.dto.trainer.response.TrainerApplicationResponseDto;
import com.example.seniya_v2.dto.trainer.response.TrainerApplicationStatusResponseDto;
import com.example.seniya_v2.service.TrainerApplicationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ApplicationController {
    private final TrainerApplicationService trainerApplicationService;

    private final String pattern = ApiMappingPattern.TRAINER_APPLY_API;

    @PostMapping(pattern)
    public ResponseEntity<ResponseDto<TrainerApplicationStatusResponseDto>> applyTrainer(@AuthenticationPrincipal String username) {
        ResponseDto<TrainerApplicationStatusResponseDto> response = trainerApplicationService.applyTrainer(username);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(pattern + "/me")
    public ResponseEntity<ResponseDto<TrainerApplicationStatusResponseDto>> getMyApplication(@AuthenticationPrincipal String username) {
        ResponseDto<TrainerApplicationStatusResponseDto> response = trainerApplicationService.getMyApplication(username);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/admin" + pattern)
    public ResponseEntity<ResponseDto<List<TrainerApplicationResponseDto>>> getAllApplication() {
        ResponseDto<List<TrainerApplicationResponseDto>> response = trainerApplicationService.getAllApplication();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/admin" + pattern + "/{id}")
    public ResponseEntity<ResponseDto<TrainerApplicationDetailResponseDto>> getApplicationById(@PathVariable Long id) {
        ResponseDto<TrainerApplicationDetailResponseDto> response = trainerApplicationService.getApplicationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/admin" + pattern + "/{id}")
    public ResponseEntity<ResponseDto<TrainerApplicationStatusResponseDto>> updateStatus(@PathVariable Long id, @RequestBody TrainerApplicationStatusRequestDto dto) {
        ResponseDto<TrainerApplicationStatusResponseDto> response = trainerApplicationService.updateStatus(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
