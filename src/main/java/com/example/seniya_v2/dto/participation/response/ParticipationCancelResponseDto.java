package com.example.seniya_v2.dto.participation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParticipationCancelResponseDto {
    private String message;
    private ParticipationInfoResponseDto data;
}
