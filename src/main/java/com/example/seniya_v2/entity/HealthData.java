package com.example.seniya_v2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "health_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter @Getter
@Builder
public class HealthData extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long healthDataId;

    @OneToMany
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private Float height;
    private Float weight;
    private Float bodyFatPercentage;

    @Enumerated(EnumType.STRING)
    private BloodPressure bloodPressure;

    @OneToMany(mappedBy = "healthData")
    private


}
