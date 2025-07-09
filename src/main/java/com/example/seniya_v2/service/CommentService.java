package com.example.seniya_v2.service;

import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.comment.request.CommentCreateRequestDto;
import com.example.seniya_v2.dto.comment.request.CommentUpdateRequestDto;
import com.example.seniya_v2.dto.comment.response.CommentCreateResponseDto;
import com.example.seniya_v2.dto.comment.response.CommentUpdateResponseDto;
import jakarta.validation.Valid;

public interface CommentService {
    ResponseDto<CommentCreateResponseDto> createComment(Long postId, @Valid CommentCreateRequestDto dto, String username);

    ResponseDto<CommentUpdateResponseDto> updateComment(Long postId, Long commentId, @Valid CommentUpdateRequestDto dto);

    void deleteComment(Long postId, Long commentId);
}
