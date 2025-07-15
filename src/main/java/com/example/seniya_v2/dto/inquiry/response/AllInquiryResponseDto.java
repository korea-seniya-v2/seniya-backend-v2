package com.example.seniya_v2.dto.inquiry.response;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllInquiryResponseDto {
    private Long id;
    private String username;
    private String title;
    private Boolean isPrivated;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
