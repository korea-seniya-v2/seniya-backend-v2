package com.example.seniya_v2.service;

import com.example.seniya_v2.dto.participation.response.ParticipationCancelResponseDto;
import com.example.seniya_v2.dto.participation.response.ParticipationInfoResponseDto;
import com.example.seniya_v2.dto.participation.response.ParticipationResponseDto;

import java.util.List;

public interface ParticipationService {
    List<ParticipationResponseDto> getMyParticipation(String username);
    ParticipationCancelResponseDto cancelParticipation(String username, Long participationId);
    ParticipationInfoResponseDto getParticipationInfo(String username, Long participationId);

}
