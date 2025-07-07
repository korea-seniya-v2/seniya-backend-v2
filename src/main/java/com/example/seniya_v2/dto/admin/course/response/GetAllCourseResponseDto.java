package com.example.seniya_v2.dto.admin.course.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class GetAllCourseResponseDto {
    private String name;
    private String title;
    private LocalDateTime classDate;
    private Category category;
    private String classroom;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
