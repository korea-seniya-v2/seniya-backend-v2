package com.example.seniya_v2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medications")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Medication {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicationId;

    @ManyToOne
    @JoinColumn(name = "disease_id")
    private Disease disease;

    private String medicationName;

    @ManyToOne
    @JoinColumn(name = "health_data_id")
    private HealthData healthData;
}
