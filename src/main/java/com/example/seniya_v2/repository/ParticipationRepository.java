package com.example.seniya_v2.repository;

import com.example.seniya_v2.entity.Course;
import com.example.seniya_v2.entity.Participation;
import com.example.seniya_v2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    List<Participation> findAllByUser_Username(String username);
    Optional<Participation> findByParticipationIdAndUser_Username(Long participationId, String username);
    boolean existsByUserAndCourse(User user, Course course);
}
