package com.example.seniya_v2.repository;

import com.example.seniya_v2.entity.HealthData;
import com.example.seniya_v2.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    List<Medication> findAllByHealthData(HealthData healthData);
}
