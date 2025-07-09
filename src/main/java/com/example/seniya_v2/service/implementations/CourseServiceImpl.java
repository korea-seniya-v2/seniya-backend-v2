package com.example.seniya_v2.service.implementations;

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
                .orElseThrow(() -> new EntityNotFoundException("X"));

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
                .
    }

    @Override
    public ResponseDto<List<CourseResponseDto>> geAllCourses() {
        return null;
    }

    @Override
    public ResponseDto<GetCourseDetailResponseDto> getCourseById(Long id) {
        return null;
    }

    @Override
    public void deleteCourse(Long id) {

    }
}
