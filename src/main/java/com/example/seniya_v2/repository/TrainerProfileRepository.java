package com.example.seniya_v2.repository;

import com.example.seniya_v2.entity.TrainerProfile;
import com.example.seniya_v2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerProfileRepository extends JpaRepository<TrainerProfile, Long> {
    TrainerProfile findByUser(User user);
}
