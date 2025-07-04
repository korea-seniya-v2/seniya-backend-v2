package com.example.seniya_v2.dto.inquiry.response;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyInquiryResponseDto {
    private String title;
    private String content;
    private String response;
    private Boolean isPrivated;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
