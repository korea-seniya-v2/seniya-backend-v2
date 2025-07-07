package com.example.seniya_v2.dto.healthdata.medication.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MedicationResponseDto {
    private Long medicationId;
    private String medicationName;

    public static MedicationResponseDto from(Medication medication) {
        return MedicationResponseDto.builder()
                .medicationId(medication.getMedicationId())
                .medicationName(medication.getMedicationName())
                .build();
    }
}
