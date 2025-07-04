package com.example.seniya_v2.dto.inquiry.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class InquiryResponseDto {
    private Long inquiryId;
    private String title;
    private String content;
    private Boolean isPrivated;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
