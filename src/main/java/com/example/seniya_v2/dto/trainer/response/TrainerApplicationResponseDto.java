package com.example.seniya_v2.dto.trainer.response;

import com.example.seniya_v2.common.enums.ApprovalStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerApplicationResponseDto {
    private Long id;
    private String username;
    private String name;
    private LocalDate appliedDate;
    private ApprovalStatus approvalStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
