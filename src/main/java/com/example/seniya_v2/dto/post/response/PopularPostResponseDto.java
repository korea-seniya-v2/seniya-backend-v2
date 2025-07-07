package com.example.seniya_v2.dto.post.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class PopularPostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private String userName;
    private LocalDateTime createdAt;

    public static PopularPostResponseDto fromEntity(Post post) {
        return PopularPostResponseDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .userName(post.getUser().getUsername())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
