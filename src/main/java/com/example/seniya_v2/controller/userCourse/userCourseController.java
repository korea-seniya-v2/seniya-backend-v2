package com.example.seniya_v2.controller.userCourse;

import com.example.seniya_v2.common.constants.ApiMappingPattern;
import com.example.seniya_v2.common.enums.Category;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.course.response.CourseApplyResponseDto;
import com.example.seniya_v2.dto.course.response.CourseDetailResponseDto;
import com.example.seniya_v2.dto.course.response.CourseListResponseDto;
import com.example.seniya_v2.service.UserCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.USER_COURSE_API)
@RequiredArgsConstructor
public class userCourseController {

    private final UserCourseService userCourseService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<CourseListResponseDto>>> getAllCourses() {
        ResponseDto<List<CourseListResponseDto>> response = userCourseService.getAllCourses();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<CourseDetailResponseDto>> getUserCourseById(@PathVariable Long id) {
        ResponseDto<CourseDetailResponseDto> response = userCourseService.getUserCourseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseDto<CourseApplyResponseDto>> applyCourse(@AuthenticationPrincipal String username, @PathVariable Long id) {
        ResponseDto<CourseApplyResponseDto> response = userCourseService.applyCourse(username, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(params = "category")
    public ResponseEntity<ResponseDto<List<CourseListResponseDto>>> getCoursesByCategory(@RequestParam(required = false) Category category) {
        ResponseDto<List<CourseListResponseDto>> response = userCourseService.getCoursesByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(params = "trainerName")
    public ResponseEntity<ResponseDto<List<CourseListResponseDto>>> getCoursesByTrainerName(@RequestParam String trainerName) {
        ResponseDto<List<CourseListResponseDto>> response = userCourseService.getCoursesByTrainerName(trainerName);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
