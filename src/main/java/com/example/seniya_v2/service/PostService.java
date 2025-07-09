package com.example.seniya_v2.service;

import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.post.request.PostCreateRequestDto;
import com.example.seniya_v2.dto.post.request.PostUpdateRequestDto;
import com.example.seniya_v2.dto.post.response.PopularPostResponseDto;
import com.example.seniya_v2.dto.post.response.PostDetailResponseDto;
import com.example.seniya_v2.dto.post.response.PostListResponseDto;
import com.example.seniya_v2.dto.post.response.PostResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    ResponseDto<PostResponseDto> createPost(String username, PostCreateRequestDto dto, List<MultipartFile> files) throws IOException;

    ResponseDto<PostDetailResponseDto> updatePost(String username, Long postId, PostUpdateRequestDto dto, List<MultipartFile> files) throws IOException;

    ResponseDto<?> deletePost(String username, Long postId);

    ResponseDto<List<PostListResponseDto>> getAllPosts();

    ResponseDto<PostDetailResponseDto> getPostById(Long id);

    ResponseDto<List<PostListResponseDto>> searchByTitle(String title);

    ResponseDto<List<PostListResponseDto>> searchByRole(String roleName);

    ResponseDto<List<PopularPostResponseDto>> getPopularPosts(int limit);

}
