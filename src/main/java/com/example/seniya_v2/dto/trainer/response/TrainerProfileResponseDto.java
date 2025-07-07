package com.example.seniya_v2.dto.trainer.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerProfileResponseDto {
    private String name;
    private Specialty specialty;
    private List<CertificateResponseDto> certificates;
    private Integer experienceYears;
    private String description;
    private String profileImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
