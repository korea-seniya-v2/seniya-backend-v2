package com.example.seniya_v2.dto.comment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CommentUpdateResponseDto {
    private Long commentId;
    private String name;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
