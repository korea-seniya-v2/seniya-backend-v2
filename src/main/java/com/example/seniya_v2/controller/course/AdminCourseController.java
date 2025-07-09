package com.example.seniya_v2.controller.course;

import com.example.seniya_v2.common.constants.ApiMappingPattern;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.admin.course.request.CreateCourseRequestDto;
import com.example.seniya_v2.dto.admin.course.request.UpdateCourseRequestDto;
import com.example.seniya_v2.dto.admin.course.response.CourseResponseDto;
import com.example.seniya_v2.dto.admin.course.response.GetCourseDetailResponseDto;
import com.example.seniya_v2.dto.admin.course.response.UpdateCourseResponseDto;
import com.example.seniya_v2.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.ADMIN_COURSE_API)
public class AdminCourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<ResponseDto<CourseResponseDto>> createCourse(@Valid @RequestBody CreateCourseRequestDto dto) {
        ResponseDto<CourseResponseDto> response = courseService.createCourse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<UpdateCourseResponseDto>> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCourseRequestDto dto
    ) {
        ResponseDto<UpdateCourseResponseDto> response = courseService.updateCourse(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<CourseResponseDto>>> getAllCourses() {
        ResponseDto<List<CourseResponseDto>> response = courseService.geAllCourses();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<GetCourseDetailResponseDto>> getCourseById(@PathVariable Long id) {
        ResponseDto<GetCourseDetailResponseDto> response = courseService.getCourseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
