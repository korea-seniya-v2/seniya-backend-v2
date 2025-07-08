package com.example.seniya_v2.repository;

import com.example.seniya_v2.entity.HealthData;
import com.example.seniya_v2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HealthDataRepository extends JpaRepository<HealthData, Long> {
    Optional<HealthData> findByUser(User user);
    boolean existsByUser(User user);
}
