package com.example.seniya_v2.repository;

import com.example.seniya_v2.entity.Disease;
import com.example.seniya_v2.entity.HealthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    List<Disease> findAllByHealthData(HealthData healthData);
}
