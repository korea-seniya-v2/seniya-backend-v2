package com.example.seniya_v2.controller.participationController;

import com.example.seniya_v2.common.constants.ApiMappingPattern;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.participation.response.ParticipationCancelResponseDto;
import com.example.seniya_v2.dto.participation.response.ParticipationResponseDto;
import com.example.seniya_v2.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.PARTICIPATION_API)
public class ParticipationController {

    private final ParticipationService participationService;

    @GetMapping("/me")
    public ResponseEntity<ResponseDto<List<ParticipationResponseDto>>> getMyParticipation(@AuthenticationPrincipal String username) {
        ResponseDto<List<ParticipationResponseDto>> response = participationService.getMyParticipation(username);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ParticipationResponseDto>> getParticipationInfo(
            @AuthenticationPrincipal String username,
            @PathVariable Long id
    ) {
        ResponseDto<ParticipationResponseDto> response = participationService.getParticipationInfo(username, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> cancelParticipation(
            @AuthenticationPrincipal String username,
            @PathVariable Long id
    ) {
        participationService.cancelParticipation(username, id);
        return ResponseEntity.noContent().build();
    }
}
