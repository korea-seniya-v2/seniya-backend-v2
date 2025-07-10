package com.example.seniya_v2.service;

import com.example.seniya_v2.common.enums.Category;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.admin.course.response.CourseResponseDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CourseFilterService {
    ResponseDto<List<CourseResponseDto>> getTodayCourses();

    ResponseDto<List<CourseResponseDto>> quickSearchCourses(Category category, String trainer, LocalDate classDate, LocalTime classStartTime, LocalTime classEndTime);
}
