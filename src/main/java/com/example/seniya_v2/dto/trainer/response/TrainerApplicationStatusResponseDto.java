package com.example.seniya_v2.dto.trainer.response;

import com.example.seniya_v2.common.enums.ApprovalStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerApplicationStatusResponseDto {
    private Long applicationId;
    private ApprovalStatus approvalStatus;
    private LocalDate appliedDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
