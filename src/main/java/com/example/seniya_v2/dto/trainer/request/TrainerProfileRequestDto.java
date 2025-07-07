package com.example.seniya_v2.dto.trainer.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TrainerProfileRequestDto {
    private Specialty specialty;
    private List<CertificateRequestDto> certificates;
    private Integer experienceYears;
    private String description;
}
