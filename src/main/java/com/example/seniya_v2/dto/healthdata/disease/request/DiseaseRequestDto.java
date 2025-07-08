package com.example.seniya_v2.dto.healthdata.disease.request;

import com.example.seniya_v2.common.enums.DiseaseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiseaseRequestDto {
    private String diseaseName;
    private LocalDate diseaseDate;
    private DiseaseStatus diseaseStatus;
}
