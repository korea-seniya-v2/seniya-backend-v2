package com.example.seniya_v2.dto.admin.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor
public class GetUserCourseResponseDto {
    private Long courseId;
    private String title;
    private String description;
    private LocalDateTime courseDate;
    private LocalTime courseStartTime;
    private LocalTime courseEndTime;
    private Category category;
    private LocalDateTime courseCreatedAt;
    private LocalDateTime courseUpdatedAt;
    private Long trainerId;
    private String trainerName;
}
