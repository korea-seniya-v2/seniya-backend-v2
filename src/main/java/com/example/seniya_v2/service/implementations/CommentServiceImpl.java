package com.example.seniya_v2.service.implementations;

import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.comment.request.CommentCreateRequestDto;
import com.example.seniya_v2.dto.comment.request.CommentUpdateRequestDto;
import com.example.seniya_v2.dto.comment.response.CommentCreateResponseDto;
import com.example.seniya_v2.dto.comment.response.CommentUpdateResponseDto;
import com.example.seniya_v2.entity.Comment;
import com.example.seniya_v2.entity.Post;
import com.example.seniya_v2.entity.User;
import com.example.seniya_v2.repository.CommentRepository;
import com.example.seniya_v2.repository.PostRepository;
import com.example.seniya_v2.repository.UserRepository;
import com.example.seniya_v2.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<CommentCreateResponseDto> createComment(Long postId, CommentCreateRequestDto dto, String username) {
        CommentCreateResponseDto response = null;
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("x"));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("x"));

        Comment newComment = Comment.builder()
                .post(post)
                .user(user)
                .content(dto.getContent())
                .build();

        commentRepository.save(newComment);

        response = CommentCreateResponseDto.builder()
                .commentId(newComment.getCommentId())
                .name(newComment.getUser().getName())
                .content(newComment.getContent())
                .createdAt(newComment.getCreatedAt())
                .updateAt(newComment.getUpdatedAt())
                .build();
        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, response).getBody();
    }

    @Override
    public ResponseDto<CommentUpdateResponseDto> updateComment(Long postId, Long commentId, CommentUpdateRequestDto dto) {
        CommentUpdateResponseDto response = null;

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("X"));

        if (!comment.getPost().getPostId().equals(postId)) {
            throw new IllegalArgumentException("x");
        }

        comment.setContent(dto.getContent());

        Comment savedComment = commentRepository.save(comment);

        response = CommentUpdateResponseDto.builder()
                .commentId(savedComment.getCommentId())
                .name(savedComment.getUser().getName())
                .content(savedComment.getContent())
                .createdAt(savedComment.getCreatedAt())
                .updateAt(savedComment.getUpdatedAt())
                .build();
        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, response).getBody();
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("x"));

        if (!comment.getPost().getPostId().equals(postId)) {
            throw new IllegalArgumentException("x");
        }

        commentRepository.delete(comment);
    }
}
