package com.example.seniya_v2.dto.inquiry.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InquiryRequestDto {
    private String title;
    private String content;
    private Boolean isPrivated;
}
