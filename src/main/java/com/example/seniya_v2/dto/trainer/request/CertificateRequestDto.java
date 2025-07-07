package com.example.seniya_v2.dto.trainer.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificateRequestDto {
    private String certificate;
    private LocalDate certificationDate;
}
