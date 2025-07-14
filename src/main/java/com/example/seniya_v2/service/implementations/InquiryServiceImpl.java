package com.example.seniya_v2.service.implementations;

import com.example.seniya_v2.common.constants.ResponseCode;
import com.example.seniya_v2.common.constants.ResponseMessage;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.inquiry.request.InquiryAnswerRequestDto;
import com.example.seniya_v2.dto.inquiry.request.InquiryRequestDto;
import com.example.seniya_v2.dto.inquiry.response.AllInquiryResponseDto;
import com.example.seniya_v2.dto.inquiry.response.InquiryByIdResponseDto;
import com.example.seniya_v2.dto.inquiry.response.InquiryResponseDto;
import com.example.seniya_v2.dto.inquiry.response.MyInquiryResponseDto;
import com.example.seniya_v2.entity.Inquiry;
import com.example.seniya_v2.entity.User;
import com.example.seniya_v2.repository.InquiryRepository;
import com.example.seniya_v2.repository.TrainerProfileRepository;
import com.example.seniya_v2.repository.UserRepository;
import com.example.seniya_v2.service.InquiryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;
    private final TrainerProfileRepository trainerProfileRepository;

    @Override
    public ResponseDto<InquiryResponseDto> createInquiry(String username, InquiryRequestDto dto) {
        InquiryResponseDto responseDto = null;

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        Inquiry newInquiry = Inquiry.builder()
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .isPrivated(dto.getIsPrivated())
                .build();

        Inquiry saved = inquiryRepository.save(newInquiry);

        responseDto = InquiryResponseDto.builder()
                .inquiryId(saved.getInquiryId())
                .title(saved.getTitle())
                .content(saved.getContent())
                .isPrivated(saved.isPrivated())
                .createdAt(saved.getCreatedAt())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, responseDto).getBody();
    }

    @Override
    public ResponseDto<List<MyInquiryResponseDto>> getMyInquiry(String username) {
        List<MyInquiryResponseDto> resDtos = null;

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        List<Inquiry> inquiries = inquiryRepository.getInquiriesByUser(user);

        resDtos = inquiries.stream()
                .map(inquiry -> MyInquiryResponseDto.builder()
                        .title(inquiry.getTitle())
                        .content(inquiry.getContent())
                        .response(inquiry.getResponse())
                        .isPrivated(inquiry.isPrivated())
                        .createdAt(inquiry.getCreatedAt())
                        .updatedAt(inquiry.getUpdatedAt())
                        .build()
                ).collect(Collectors.toList());

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, resDtos).getBody();
    }

    @Override
    public ResponseDto<List<AllInquiryResponseDto>> getAllInquiry() {
        List<AllInquiryResponseDto> resDtos = null;

        List<Inquiry> inquiries = inquiryRepository.findAll();

        resDtos = inquiries.stream()
                .map(inquiry -> AllInquiryResponseDto.builder()
                        .id(inquiry.getInquiryId())
                        .username(inquiry.getUser().getName())
                        .title(inquiry.getTitle())
                        .content(inquiry.getContent())
                        .isPrivated(inquiry.isPrivated())
                        .createdAt(inquiry.getCreatedAt())
                        .updatedAt(inquiry.getUpdatedAt())
                        .build()
                ).collect(Collectors.toList());

        return  ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, resDtos).getBody();
    }

    @Override
    public ResponseDto<InquiryByIdResponseDto> getInquiryDetail(String username, Long id) throws AccessDeniedException {
        InquiryByIdResponseDto responseDto = null;

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("INQUIRY NOT FOUND"));

        String roleName = user.getRole().getRoleName();
        if(inquiry.isPrivated()) {
            if (!roleName.equals("TRAINER") && !roleName.equals("ADMIN")) {
                if (!inquiry.getUser().equals(user)) {
                    throw new IllegalArgumentException(ResponseMessage.NO_PERMISSION);
                }
            }
        }

        responseDto = InquiryByIdResponseDto.builder()
                .title(inquiry.getTitle())
                .username(inquiry.getUser().getName())
                .trainerName(inquiry.getTrainer() != null ? inquiry.getTrainer().getUser().getName() : null)
                .content(inquiry.getContent())
                .response(inquiry.getResponse())
                .isPrivated(inquiry.isPrivated())
                .createdAt(inquiry.getCreatedAt())
                .updatedAt(inquiry.getUpdatedAt())
                .build();

        return  ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, responseDto).getBody();
    }

    @Override
    public ResponseDto<InquiryResponseDto> updateInquiry(String username, Long id, InquiryRequestDto dto) {
        return null;
    }

    @Override
    public ResponseDto<?> deleteInquiry(String username, Long id) {
        return null;
    }

    @Override
    public ResponseDto<InquiryByIdResponseDto> inquiryAnswer(String username, Long id, InquiryAnswerRequestDto dto) {
        return null;
    }
}
