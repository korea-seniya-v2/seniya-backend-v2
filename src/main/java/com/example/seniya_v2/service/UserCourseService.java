package com.example.seniya_v2.service;

import com.example.seniya_v2.common.enums.Category;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.course.response.CourseApplyResponseDto;
import com.example.seniya_v2.dto.course.response.CourseDetailResponseDto;
import com.example.seniya_v2.dto.course.response.CourseListResponseDto;

import java.util.List;

public interface UserCourseService {
    ResponseDto<List<CourseListResponseDto>> getAllCourses();

    ResponseDto<CourseDetailResponseDto> getUserCourseById(Long id);

    ResponseDto<CourseApplyResponseDto> applyCourse(String username, Long id);

    ResponseDto<List<CourseListResponseDto>> getCoursesByCategory(Category category);

    ResponseDto<List<CourseListResponseDto>> getCoursesByTrainerName(String trainerName);
}
