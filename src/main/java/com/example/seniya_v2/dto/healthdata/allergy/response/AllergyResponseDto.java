package com.example.seniya_v2.dto.healthdata.allergy.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AllergyResponseDto {
    private Long allergyId;
    private String allergyName;
    private String reaction;

    public static AllergyResponseDto from(Allergy allergy) {
        return AllergyResponseDto.builder()
                .allergyId(allergy.getAllergyId())
                .allergyName(allergy.getAllergyName())
                .reaction(allergy.getReaction())
                .build();
    }
}
