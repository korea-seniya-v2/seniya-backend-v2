package com.example.seniya_v2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "allergies")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter @Getter
@Builder
public class Allergy {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allergyId;

    private String allergyName;
    private String reaction;

    @ManyToOne
    @JoinColumn(name = "health_data_id")
    private HealthData healthData;
}
