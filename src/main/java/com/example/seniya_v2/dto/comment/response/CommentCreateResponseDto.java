package com.example.seniya_v2.dto.comment.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentCreateResponseDto {
    private Long commentId;
    private String name;
    private Long postId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;

    public CommentCreateResponseDto(Long commentId, Long postId, String name, String content, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.postId = postId;
        this.name = name;
        this.content = content;
        this.createdAt = createdAt;
    }
}
