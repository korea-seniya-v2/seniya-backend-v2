package com.example.seniya_v2.dto.inquiry.response;

import com.example.seniya_v2.common.enums.InquiryStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InquiryByIdResponseDto {
    private String title;
    private String username;
    private String content;
    private String admin;
    private String answer;
    private InquiryStatus status;
    private List<String> inquiryImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
