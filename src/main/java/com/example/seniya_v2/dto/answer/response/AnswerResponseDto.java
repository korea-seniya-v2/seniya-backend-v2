package com.example.seniya_v2.dto.answer.response;

import com.example.seniya_v2.common.enums.InquiryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class AnswerResponseDto {
    private String userName;
    private String title;
    private String inquiryContent;
    private List<String> inquiryImageUrl;
    private String admin;
    private String answerContent;
    private InquiryStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
