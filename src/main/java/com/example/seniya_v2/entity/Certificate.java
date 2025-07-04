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
    private Long certificate_id;

    @ManyToOne(fetch = FetchType.LAZY)
    private TrainerProfile trainerProfile;

    private String certificate;
    private LocalDate certificationDate;
}
