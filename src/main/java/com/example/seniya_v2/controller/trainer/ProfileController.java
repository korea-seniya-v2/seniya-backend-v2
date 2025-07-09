package com.example.seniya_v2.controller.trainer;

import com.example.seniya_v2.common.constants.ApiMappingPattern;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.trainer.request.TrainerProfileRequestDto;
import com.example.seniya_v2.dto.trainer.request.UpdateTrainerProfileRequestDto;
import com.example.seniya_v2.dto.trainer.response.PopularTrainerResponseDto;
import com.example.seniya_v2.dto.trainer.response.TrainerProfileCreateResponseDto;
import com.example.seniya_v2.dto.trainer.response.TrainerProfileResponseDto;
import com.example.seniya_v2.service.TrainerProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.NoPermissionException;
import java.io.IOException;

@RestController
@RequestMapping(ApiMappingPattern.TRAINER_PROFILE_API)
@RequiredArgsConstructor
public class ProfileController {
    private final TrainerProfileService trainerProfileService;

    @PostMapping("/me")
    public ResponseEntity<ResponseDto<TrainerProfileCreateResponseDto>> createProfile(
            @AuthenticationPrincipal String username,
            @RequestPart(value = "dto") @Valid TrainerProfileRequestDto dto,
            @RequestPart(value = "file", required = false) MultipartFile file
            ) throws NoPermissionException, IOException {
        ResponseDto<TrainerProfileCreateResponseDto> response = trainerProfileService.createProfile(username, dto, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseDto<TrainerProfileResponseDto>> getTrainerProfile(@AuthenticationPrincipal String username) throws NoPermissionException {
        ResponseDto<TrainerProfileResponseDto> response = trainerProfileService.getTrainerProfile(username);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/me")
    public ResponseEntity<ResponseDto<TrainerProfileResponseDto>> updateProfile(
            @AuthenticationPrincipal String username,
            @RequestPart(value = "dto") @Valid UpdateTrainerProfileRequestDto dto,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws NoPermissionException, IOException {
        ResponseDto<TrainerProfileResponseDto> response = trainerProfileService.updateProfile(username, dto, file);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/popular")
    public ResponseEntity<ResponseDto<PopularTrainerResponseDto>> popularTrainer() {
        ResponseDto<PopularTrainerResponseDto> response = trainerProfileService.popularTrainer();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
