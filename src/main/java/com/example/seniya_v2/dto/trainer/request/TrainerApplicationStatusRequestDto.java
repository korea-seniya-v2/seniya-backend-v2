package com.example.seniya_v2.dto.trainer.request;

import com.example.seniya_v2.common.enums.ApprovalStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TrainerApplicationStatusRequestDto {
    private ApprovalStatus approvalStatus;
}
