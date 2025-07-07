package com.example.seniya_v2.dto.healthdata.disease.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class DiseaseResponseDto {
    private Long diseaseId;
    private String diseaseName;
    private LocalDate diseaseDate;
    private DiseaseStatus diseaseStatus;

    public static DiseaseResponseDto from(Disease disease) {
        return DiseaseResponseDto.builder()
                .diseaseId(disease.getDiseaseId())
                .diseaseName(disease.getDiseaseName())
                .diseaseDate(disease.getDiseaseDate())
                .diseaseStatus(disease.getDiseaseStatus())
                .build();
    }
}
