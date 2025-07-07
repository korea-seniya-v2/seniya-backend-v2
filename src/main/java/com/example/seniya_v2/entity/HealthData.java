package com.example.seniya_v2.entity;

import com.example.seniya_v2.common.enums.BloodPressure;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "health_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter @Getter
@Builder
public class HealthData extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_data_id", nullable = false)
    private Long healthDataId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Column(name = "height", nullable = false)
    private Float height;

    @Column(name = "weight", nullable = false)
    private Float weight;

    @Column(name = "body_fat_percentage")
    private Float bodyFatPercentage;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_pressure")
    private BloodPressure bloodPressure;

    @OneToMany(mappedBy = "healthData", cascade = CascadeType.ALL)
    private List<Disease> diseases;

    @OneToMany(mappedBy = "healthData", cascade = CascadeType.ALL)
    private List<Medication> medication;

    @OneToMany(mappedBy = "healthData", cascade = CascadeType.ALL)
    private List<Allergy> allergy;

    @Builder.Default
    @Column(name = "smoking", nullable = false)
    private boolean smoking = false;

    @Builder.Default
    @Column(name = "drinking", nullable = false)
    private boolean drinking = false;
}
