package com.example.seniya_v2.dto.healthdata.allergy.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllergyRequestDto {
    private String allergyName;
    private String reaction;
}
