package com.example.seniya_v2.service.implementations;

import com.example.seniya_v2.common.constants.ResponseCode;
import com.example.seniya_v2.common.constants.ResponseMessage;
import com.example.seniya_v2.common.enums.uploadFile.TargetType;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.inquiry.request.InquiryRequestDto;
import com.example.seniya_v2.dto.inquiry.response.AllInquiryResponseDto;
import com.example.seniya_v2.dto.inquiry.response.InquiryByIdResponseDto;
import com.example.seniya_v2.dto.inquiry.response.InquiryResponseDto;
import com.example.seniya_v2.dto.inquiry.response.MyInquiryResponseDto;
import com.example.seniya_v2.entity.Answer;
import com.example.seniya_v2.entity.Inquiry;
import com.example.seniya_v2.entity.UploadFile;
import com.example.seniya_v2.entity.User;
import com.example.seniya_v2.repository.InquiryRepository;
import com.example.seniya_v2.repository.TrainerProfileRepository;
import com.example.seniya_v2.repository.UploadFileRepository;
import com.example.seniya_v2.repository.UserRepository;
import com.example.seniya_v2.service.InquiryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;
    private final TrainerProfileRepository trainerProfileRepository;
    private final UploadFileRepository fileRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    private void saveFiles(List<MultipartFile> files, Long targetId, TargetType type) throws IOException {
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdir();
        }

        for (MultipartFile file : files) {
            String original = file.getOriginalFilename();
            String uuid = UUID.randomUUID() + "_" + original;
            file.transferTo(new File(uploadDir + "/" + uuid));

            UploadFile uf = UploadFile.builder()
                    .originalName(original)
                    .fileName(uuid)
                    .filePath("/files/" + uuid)
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .targetId(targetId)
                    .targetType(type)
                    .build();
            fileRepository.save(uf);
        }
    }

    @Override
    public ResponseDto<InquiryResponseDto> createInquiry(String username, InquiryRequestDto dto, List<MultipartFile> files) throws IOException {
        InquiryResponseDto response = null;

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        Inquiry newInquiry = Inquiry.builder()
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();

        inquiryRepository.save(newInquiry);

        if(files != null && !files.isEmpty()) {
            saveFiles(files, newInquiry.getInquiryId(), TargetType.INQUIRY);
        }

        List<UploadFile> inquiryImage = fileRepository.findByTargetIdAndTargetType(newInquiry.getInquiryId(), TargetType.INQUIRY);
        List<String> imageUrl = inquiryImage.stream()
                .map(UploadFile::getFilePath)
                .collect(Collectors.toList());

        response = InquiryResponseDto.builder()
                .inquiryId(newInquiry.getInquiryId())
                .title(newInquiry.getTitle())
                .content(newInquiry.getContent())
                .InquiryImageUrl(imageUrl)
                .createdAt(newInquiry.getCreatedAt())
                .updatedAt(newInquiry.getUpdatedAt())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, response).getBody();
    }

    @Override
    public ResponseDto<List<MyInquiryResponseDto>> getMyInquiry(String username) {
        List<MyInquiryResponseDto> response = null;

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        List<Inquiry> inquiries = inquiryRepository.getInquiriesByUser(user);

        response = inquiries.stream()
                .map(inquiry -> MyInquiryResponseDto.builder()
                        .inquiryId(inquiry.getInquiryId())
                        .title(inquiry.getTitle())
                        .status(inquiry.getStatus())
                        .createdAt(inquiry.getCreatedAt())
                        .updatedAt(inquiry.getUpdatedAt())
                        .build()
                ).collect(Collectors.toList());

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, response).getBody();
    }

    @Override
    public ResponseDto<List<AllInquiryResponseDto>> getAllInquiry(String username) {
        List<AllInquiryResponseDto> response = null;

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        String role = user.getRole().getRoleName();

        if (!role.equals("ADMIN")) {
            throw new IllegalArgumentException(ResponseMessage.NO_PERMISSION);
        }

        List<Inquiry> inquiries = inquiryRepository.findAll();

        response = inquiries.stream()
                .map(inquiry -> AllInquiryResponseDto.builder()
                        .inquiryId(inquiry.getInquiryId())
                        .title(inquiry.getTitle())
                        .userName(inquiry.getUser().getName())
                        .status(inquiry.getStatus())
                        .createdAt(inquiry.getCreatedAt())
                        .updatedAt(inquiry.getUpdatedAt())
                        .build()).collect(Collectors.toList());

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, response).getBody();
    }

    @Override
    public ResponseDto<InquiryByIdResponseDto> getInquiryDetail(String username, Long id) {
        InquiryByIdResponseDto response = null;

        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));

        List<UploadFile> inquiryImage = fileRepository.findByTargetIdAndTargetType(inquiry.getInquiryId(), TargetType.INQUIRY);
        List<String> imageUrl = inquiryImage.stream()
                .map(UploadFile::getFilePath)
                .collect(Collectors.toList());

        Answer answer = inquiry.getAnswer();
        String admin = null;
        String answerContent = null;

        if(answer != null) {
            admin = answer.getUser().getName();
            answerContent = answer.getContent();
        }

        response = InquiryByIdResponseDto.builder()
                .title(inquiry.getTitle())
                .username(inquiry.getUser().getName())
                .content(inquiry.getContent())
                .admin(admin)
                .status(inquiry.getStatus())
                .answer(answerContent)
                .inquiryImageUrl(imageUrl)
                .createdAt(inquiry.getCreatedAt())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, response).getBody();
    }

    @Override
    public ResponseDto<?> deleteInquiry(String username, Long id) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));

        if (!inquiry.getUser().equals(user)) {
            throw new IllegalArgumentException(ResponseMessage.NO_PERMISSION);
        }

        inquiryRepository.delete(inquiry);

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS).getBody();
    }
}