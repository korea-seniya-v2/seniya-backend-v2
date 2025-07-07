package com.example.seniya_v2.dto.notice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeCreateRequestDto {
    private String title;
    private String content;
}
