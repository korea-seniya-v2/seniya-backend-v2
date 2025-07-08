package com.example.seniya_v2.repository;

import com.example.seniya_v2.entity.TrainerApplication;
import com.example.seniya_v2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerApplicationRepository extends JpaRepository<TrainerApplication, Long> {
    TrainerApplication getTrainerApplicationByUser(User user);
}
