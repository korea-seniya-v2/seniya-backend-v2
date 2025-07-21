package com.example.seniya_v2.dto.inquiry.response;

import com.example.seniya_v2.common.enums.InquiryStatus;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllInquiryResponseDto {
    private Long inquiryId;
    private String title;
    private String userName;
    private InquiryStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
