package com.example.seniya_v2.dto.inquiry.response;

import com.example.seniya_v2.common.enums.InquiryStatus;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyInquiryResponseDto {
    private String title;
    private InquiryStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
