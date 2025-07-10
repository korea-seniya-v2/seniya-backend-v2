package com.example.seniya_v2.dto.participation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@Builder
public class ParticipationResponseDto {
    private String category;
    private String title;
    private String description;
    private String trainerName;
    private LocalDate courseDate;
    private LocalTime courseStartTime;
    private LocalTime courseEndTime;
    private String courseRoom;
}
