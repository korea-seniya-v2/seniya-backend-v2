package com.example.seniya_v2.dto.healthdata.request;

import com.example.seniya_v2.common.enums.BloodPressure;
import com.example.seniya_v2.dto.healthdata.allergy.request.AllergyRequestDto;
import com.example.seniya_v2.dto.healthdata.disease.request.DiseaseRequestDto;
import com.example.seniya_v2.dto.healthdata.medication.request.MedicationRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthDataUpdRequestDto {
    private Long healthDataId;
    private Float height;
    private Float weight;
    private Float bodyFatPercentage;
    private BloodPressure bloodPressure;

    private List<DiseaseRequestDto> diseases;
    private List<MedicationRequestDto> medications;
    private List<AllergyRequestDto> allergies;

    private Boolean smoking;
    private Boolean drinking;
}
