package com.example.seniya_v2.dto.trainer.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerApplicationDetailResponseDto {
    private String username;
    private String name;
    private String userEmail;
    private LocalDate appliedDate;
    private ApprovalStatus approvalStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
