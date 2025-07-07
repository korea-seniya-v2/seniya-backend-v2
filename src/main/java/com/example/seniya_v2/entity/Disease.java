package com.example.seniya_v2.entity;

import com.example.seniya_v2.common.enums.DiseaseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "diseases")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter @Getter
@Builder
public class Disease {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long diseaseId;

    private String diseaseName;
    private LocalDate diseaseDate;

    @ManyToOne
    @JoinColumn(name = "health_date_id")
    private HealthData healthData;

    @OneToMany(mappedBy = "disease", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medication> medication;

    @Enumerated(EnumType.STRING)
    @Column(name = "diseaseStatus", nullable = false)
    private DiseaseStatus diseaseStatus;
}
