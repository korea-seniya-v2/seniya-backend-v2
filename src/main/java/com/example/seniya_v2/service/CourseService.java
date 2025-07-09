package com.example.seniya_v2.service;

import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.admin.course.request.CreateCourseRequestDto;
import com.example.seniya_v2.dto.admin.course.request.UpdateCourseRequestDto;
import com.example.seniya_v2.dto.admin.course.response.CourseResponseDto;
import com.example.seniya_v2.dto.admin.course.response.GetCourseDetailResponseDto;
import com.example.seniya_v2.dto.admin.course.response.UpdateCourseResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface CourseService {
    ResponseDto<CourseResponseDto> createCourse(@Valid CreateCourseRequestDto dto);

    ResponseDto<UpdateCourseResponseDto> updateCourse(Long id, @Valid UpdateCourseRequestDto dto);

    ResponseDto<List<CourseResponseDto>> geAllCourses();

    ResponseDto<GetCourseDetailResponseDto> getCourseById(Long id);

    void deleteCourse(Long id);
}
