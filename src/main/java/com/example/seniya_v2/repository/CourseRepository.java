package com.example.seniya_v2.repository;

import com.example.seniya_v2.common.enums.Category;
import com.example.seniya_v2.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCategory(Category category);
    List<Course> findByTrainerProfile_User_Name(String trainerName);
}
