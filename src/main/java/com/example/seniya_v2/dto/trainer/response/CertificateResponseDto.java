package com.example.seniya_v2.dto.trainer.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificateResponseDto {
    private String certificate;
    private LocalDate certificationDate;
}
