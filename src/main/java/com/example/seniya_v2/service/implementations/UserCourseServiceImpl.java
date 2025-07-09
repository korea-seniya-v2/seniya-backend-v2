package com.example.seniya_v2.service.implementations;

import com.example.seniya_v2.common.constants.ResponseCode;
import com.example.seniya_v2.common.constants.ResponseMessage;
import com.example.seniya_v2.common.enums.Category;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.course.response.CourseApplyResponseDto;
import com.example.seniya_v2.dto.course.response.CourseDetailResponseDto;
import com.example.seniya_v2.dto.course.response.CourseListResponseDto;
import com.example.seniya_v2.entity.Course;
import com.example.seniya_v2.entity.Participation;
import com.example.seniya_v2.entity.User;
import com.example.seniya_v2.repository.CourseRepository;
import com.example.seniya_v2.repository.ParticipationRepository;
import com.example.seniya_v2.repository.UserRepository;
import com.example.seniya_v2.service.UserCourseService;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCourseServiceImpl implements UserCourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ParticipationRepository participationRepository;

    @Override
    public ResponseDto<List<CourseListResponseDto>> getAllCourses() {
        List<CourseListResponseDto> dto = null;

        List<Course> courses = courseRepository.findAll();

        dto = courses.stream()
                .map(course -> CourseListResponseDto.builder()
                        .courseId(course.getCourseId())
                        .name(course.getTrainerProfile().getUser().getName())
                        .title(course.getTitle())
                        .description(course.getDescription())
                        .classDate(course.getDate())
                        .classStartTime(course.getStartTime())
                        .classEndTime(course.getEndTime())
                        .classroom(course.getRoom())
                        .category(course.getCategory())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, dto).getBody();
    }

    @Override
    public ResponseDto<CourseDetailResponseDto> getUserCourseById(Long id) {
        CourseDetailResponseDto dto = null;

        Course course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));

        dto = CourseDetailResponseDto.builder()
                .courseId(course.getCourseId())
                .trainerId(course.getTrainerProfile().getTrainerId())
                .trainerName(course.getTrainerProfile().getUser().getName())
                .title(course.getTitle())
                .description(course.getDescription())
                .classDate(course.getDate())
                .classStartTime(course.getStartTime())
                .classEndTime(course.getEndTime())
                .classroom(course.getRoom())
                .category(course.getCategory())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, dto).getBody();
    }

    @Override
    public ResponseDto<CourseApplyResponseDto> applyCourse(String username, Long id) {
        CourseApplyResponseDto responseDto = null;

        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        Course course =  courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));

        if(participationRepository.existsByUserAndCourse(user,course)) {
            throw new DuplicateRequestException(ResponseMessage.FAILED);
        }

        Participation participation = Participation.builder()
                .user(user)
                .course(course)
                .build();
        participationRepository.save(participation);

        responseDto = CourseApplyResponseDto.builder()
                .courseId(course.getCourseId())
                .userId(user.getUserId())
                .userName(user.getUsername())
                .trainerId(course.getTrainerProfile().getTrainerId())
                .trainerName(course.getTrainerProfile().getUser().getName())
                .title(course.getTitle())
                .date(course.getDate())
                .startTime(course.getStartTime())
                .endTime(course.getEndTime())
                .room(course.getRoom())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, responseDto).getBody();
    }

    @Override
    public ResponseDto<List<CourseListResponseDto>> getCoursesByCategory(Category category) {
        List<Course> courses;

        if(category == null) {
            courses = courseRepository.findAll();
        } else {
            courses = courseRepository.findByCategory(category);
        }

        if(courses.isEmpty()) {
            throw new EntityNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND);
        }

        List<CourseListResponseDto> dto = courses.stream()
                .map(course -> CourseListResponseDto.builder()
                        .courseId(course.getCourseId())
                        .name(course.getTrainerProfile().getUser().getName())
                        .title(course.getTitle())
                        .description(course.getDescription())
                        .classDate(course.getDate())
                        .classStartTime(course.getStartTime())
                        .classEndTime(course.getEndTime())
                        .category(course.getCategory())
                        .classroom(course.getRoom())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, dto).getBody();
    }

    @Override
    public ResponseDto<List<CourseListResponseDto>> getCoursesByTrainerName(String trainerName) {
        List<Course> courses = courseRepository.findByTrainerProfile_User_Name(trainerName);

        if (courses.isEmpty()) {
            throw new EntityNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND);
        }

        List<CourseListResponseDto> dto = courses.stream()
                .map(course -> CourseListResponseDto.builder()
                        .courseId(course.getCourseId())
                        .name(course.getTrainerProfile().getUser().getName())
                        .title(course.getTitle())
                        .description(course.getDescription())
                        .classDate(course.getDate())
                        .classStartTime(course.getStartTime())
                        .classEndTime(course.getEndTime())
                        .category(course.getCategory())
                        .classroom(course.getRoom())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, dto).getBody();
    }
}
