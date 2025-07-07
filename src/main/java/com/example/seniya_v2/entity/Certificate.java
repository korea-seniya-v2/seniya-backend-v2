package com.example.seniya_v2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "certificates")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter @Getter
@Builder
public class Certificate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_id", nullable = false)
    private Long certificate_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", nullable = false)
    private TrainerProfile trainerProfile;

    @Column(name = "certificate", nullable = false)
    private String certificate;

    @Column(name = "certification_date", nullable = false)
    private LocalDate certificationDate;
}
