package com.example.seniya_v2.service.implementations;

import com.example.seniya_v2.common.constants.ResponseCode;
import com.example.seniya_v2.common.constants.ResponseMessage;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.admin.course.request.CreateCourseRequestDto;
import com.example.seniya_v2.dto.admin.course.request.UpdateCourseRequestDto;
import com.example.seniya_v2.dto.admin.course.response.CourseResponseDto;
import com.example.seniya_v2.dto.admin.course.response.GetCourseDetailResponseDto;
import com.example.seniya_v2.dto.admin.course.response.UpdateCourseResponseDto;
import com.example.seniya_v2.entity.Course;
import com.example.seniya_v2.entity.TrainerProfile;
import com.example.seniya_v2.repository.CourseRepository;
import com.example.seniya_v2.repository.TrainerProfileRepository;
import com.example.seniya_v2.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TrainerProfileRepository trainerProfileRepository;

    @Override
    public ResponseDto<CourseResponseDto> createCourse(CreateCourseRequestDto dto) {
        CourseResponseDto respDto = null;

        TrainerProfile trainerProfile = trainerProfileRepository.findById(dto.getTrainerId())
                .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.USER_NOT_FOUND));

        Course newCourse = Course.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .trainerProfile(trainerProfile)
                .date(dto.getClassDate())
                .startTime(dto.getClassStartTime())
                .endTime(dto.getClassEndTime())
                .room(dto.getClassroom())
                .build();

        Course savedCourse = courseRepository.save(newCourse);

        respDto = CourseResponseDto.builder()
                .name(savedCourse.getTrainerProfile().getUser().getName())
                .title(savedCourse.getTitle())
                .description(savedCourse.getDescription())
                .classDate(savedCourse.getDate())
                .classStartTime(savedCourse.getStartTime())
                .classEndTime(savedCourse.getEndTime())
                .category(savedCourse.getCategory())
                .classroom(savedCourse.getRoom())
                .build();
        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, respDto).getBody();
    }

    @Override
    public ResponseDto<UpdateCourseResponseDto> updateCourse(Long id, UpdateCourseRequestDto dto) {
        UpdateCourseResponseDto respDto = null;

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));

        if (dto.getTitle() != null) course.setTitle(dto.getTitle());
        if (dto.getDescription() != null) course.setDescription(dto.getDescription());
        if (dto.getCategory() != null) course.setCategory(dto.getCategory());
        if (dto.getTrainerId() != null) course.setTrainerProfile(course.getTrainerProfile());
        if (dto.getClassDate() != null) course.setDate(dto.getClassDate());
        if (dto.getClassStartTime() != null) course.setStartTime(dto.getClassStartTime());
        if (dto.getClassEndTime() != null) course.setEndTime(dto.getClassEndTime());
        if (dto.getClassroom() != null) course.setRoom(dto.getClassroom());

        Course updatedCourse = courseRepository.save(course);

        respDto = UpdateCourseResponseDto.builder()
                .name(updatedCourse.getTrainerProfile().getUser().getName())
                .title(updatedCourse.getTitle())
                .description(updatedCourse.getDescription())
                .classDate(updatedCourse.getDate())
                .classStartTime(updatedCourse.getStartTime())
                .classEndTime(updatedCourse.getEndTime())
                .category(updatedCourse.getCategory())
                .classroom(updatedCourse.getRoom())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, respDto).getBody();
    }

    @Override
    public ResponseDto<List<CourseResponseDto>> getAllCourses() {
        List<CourseResponseDto> respDto = null;

        List<Course> courses = courseRepository.findAll();

        respDto = courses.stream()
                .map(course -> CourseResponseDto.builder()
                        .id(course.getCourseId())
                        .trainerId(course.getTrainerProfile().getTrainerId())
                        .name(course.getTrainerProfile().getUser().getName())
                        .title(course.getTitle())
                        .description(course.getDescription())
                        .classDate(course.getDate())
                        .classStartTime(course.getStartTime())
                        .classEndTime(course.getEndTime())
                        .category(course.getCategory())
                        .classroom(course.getRoom())
                        .createdAt(course.getCreatedAt())
                        .updatedAt(course.getUpdatedAt())
                        .build()
                ).collect(Collectors.toList());

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, respDto).getBody();
    }

    @Override
    public ResponseDto<GetCourseDetailResponseDto> getCourseById(Long id) {
        GetCourseDetailResponseDto respDto = null;

        Course course = courseRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));

        respDto = GetCourseDetailResponseDto.builder()
                .courseId(course.getCourseId())
                .trainerId(course.getTrainerProfile().getTrainerId())
                .trainerName(course.getTrainerProfile().getUser().getName())
                .title(course.getTitle())
                .description(course.getDescription())
                .classDate(course.getDate())
                .classStartTime(course.getStartTime())
                .classEndTime(course.getEndTime())
                .category(course.getCategory())
                .classroom(course.getRoom())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, respDto).getBody();
    }

    @Override
    public ResponseDto<?> deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));

        courseRepository.delete(course);

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS).getBody();
    }
}
