package com.example.seniya_v2.service;

import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.trainer.request.TrainerProfileRequestDto;
import com.example.seniya_v2.dto.trainer.request.UpdateTrainerProfileRequestDto;
import com.example.seniya_v2.dto.trainer.response.PopularTrainerResponseDto;
import com.example.seniya_v2.dto.trainer.response.TrainerProfileCreateResponseDto;
import com.example.seniya_v2.dto.trainer.response.TrainerProfileResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.NoPermissionException;
import java.io.IOException;

public interface TrainerProfileService {
    ResponseDto<TrainerProfileCreateResponseDto> createProfile(String username, @Valid TrainerProfileRequestDto dto, MultipartFile file) throws NoPermissionException, IOException;

    ResponseDto<TrainerProfileResponseDto> getTrainerProfile(String username) throws NoPermissionException;

    ResponseDto<TrainerProfileResponseDto> updateProfile(String username, @Valid UpdateTrainerProfileRequestDto dto, MultipartFile file) throws NoPermissionException, IOException;

    ResponseDto<PopularTrainerResponseDto> popularTrainer();

}
