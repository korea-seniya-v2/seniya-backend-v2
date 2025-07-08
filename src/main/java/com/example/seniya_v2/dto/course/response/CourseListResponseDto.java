package com.example.seniya_v2.dto.course.response;

import com.example.seniya_v2.common.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor
public class CourseListResponseDto {
    private Long courseId;
    private String name;
    private String title;
    private LocalDateTime classDate;
    private LocalTime classStartTime;
    private LocalTime classEndTime;
    private Category category;
    private String classroom;
}
