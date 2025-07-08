package com.example.seniya_v2.repository;

import com.example.seniya_v2.entity.Allergy;
import com.example.seniya_v2.entity.HealthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {
    List<Allergy> findAllByHealthData(HealthData healthData);
}
