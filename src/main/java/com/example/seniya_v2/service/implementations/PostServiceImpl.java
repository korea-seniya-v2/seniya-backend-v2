package com.example.seniya_v2.service.implementations;

import com.example.seniya_v2.common.constants.ResponseCode;
import com.example.seniya_v2.common.constants.ResponseMessage;
import com.example.seniya_v2.common.enums.uploadFile.TargetType;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.post.request.PostCreateRequestDto;
import com.example.seniya_v2.dto.post.request.PostUpdateRequestDto;
import com.example.seniya_v2.dto.post.response.PopularPostResponseDto;
import com.example.seniya_v2.dto.post.response.PostDetailResponseDto;
import com.example.seniya_v2.dto.post.response.PostListResponseDto;
import com.example.seniya_v2.dto.post.response.PostResponseDto;
import com.example.seniya_v2.entity.Post;
import com.example.seniya_v2.entity.UploadFile;
import com.example.seniya_v2.entity.User;
import com.example.seniya_v2.repository.PostRepository;
import com.example.seniya_v2.repository.UploadFileRepository;
import com.example.seniya_v2.repository.UserRepository;
import com.example.seniya_v2.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UploadFileRepository uploadFileRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public ResponseDto<PostResponseDto> createPost(String username, PostCreateRequestDto dto, List<MultipartFile> files) throws IOException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        Post post = Post.builder()
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();

        post = postRepository.save(post);

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
//                    saveFile(file, post.getPostId(), TargetType.POST);
                }
            }
        }

        List<UploadFile> uploadFiles = uploadFileRepository.findByTargetIdAndTargetType(post.getPostId(), TargetType.POST);
        List<String> imageUrls = uploadFiles.stream()
                .map(UploadFile::getFilePath)
                .collect(Collectors.toList());

        PostResponseDto responseDto = PostResponseDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrls(imageUrls)
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, responseDto).getBody();
    }

    @Override
    @Transactional
    public ResponseDto<PostDetailResponseDto> updatePost(String username, Long id, PostUpdateRequestDto dto, List<MultipartFile> files) throws IOException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        return null;
    }

    @Override
    public ResponseDto<?> deletePost(String username, Long postId) {
        return null;
    }

    @Override
    public ResponseDto<List<PostListResponseDto>> getAllPosts() {
        return null;
    }

    @Override
    public ResponseDto<PostDetailResponseDto> getPostById(Long id) {
        return null;
    }

    @Override
    public ResponseDto<List<PostListResponseDto>> searchByTitle(String title) {
        return null;
    }

    @Override
    public ResponseDto<List<PostListResponseDto>> searchByRole(String roleName) {
        return null;
    }

    @Override
    public ResponseDto<List<PopularPostResponseDto>> getPopularPosts(int limit) {
        return null;
    }
}
