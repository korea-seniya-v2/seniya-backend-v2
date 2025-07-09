package com.example.seniya_v2.dto.comment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class CommentCreateResponseDto {
    private Long commentId;
    private String name;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
