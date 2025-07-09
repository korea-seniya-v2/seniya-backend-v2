package com.example.seniya_v2.controller.course;

import com.example.seniya_v2.common.constants.ApiMappingPattern;
import com.example.seniya_v2.common.enums.Category;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.admin.course.response.CourseResponseDto;
import com.example.seniya_v2.service.CourseFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.COURSE_FIlTER_API)
public class CourseFilterController {

    private final CourseFilterService courseFilterService;

    @GetMapping("/today")
    public ResponseEntity<ResponseDto<List<CourseResponseDto>>> getTodayCourses() {
        ResponseDto<List<CourseResponseDto>> response = courseFilterService.getTodayCourses();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<CourseResponseDto>>> quickSearchCourses(
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) String trainer,
            @RequestParam(required = false) LocalDate classDate,
            @RequestParam(required = false) LocalTime classStartTime,
            @RequestParam(required = false) LocalTime classEndTime
    ) {
        ResponseDto<List<CourseResponseDto>> response = courseFilterService.quickSearchCourses(category, trainer, classDate, classStartTime, classEndTime);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
