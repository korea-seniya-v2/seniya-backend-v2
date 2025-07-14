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
import com.example.seniya_v2.entity.TrainerProfile;
import com.example.seniya_v2.entity.User;
import com.example.seniya_v2.repository.InquiryRepository;
import com.example.seniya_v2.repository.TrainerProfileRepository;
import com.example.seniya_v2.repository.UserRepository;
import com.example.seniya_v2.service.InquiryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        InquiryResponseDto response = null;

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException((ResponseMessage.USER_NOT_FOUND)));

        Inquiry newInquiry = Inquiry.builder()
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .isPrivated(dto.getIsPrivated())
                .build();

        Inquiry savedInquiry = inquiryRepository.save(newInquiry);

        response = InquiryResponseDto.builder()
                .inquiryId(savedInquiry.getInquiryId())
                .title(savedInquiry.getTitle())
                .content(savedInquiry.getContent())
                .isPrivated(savedInquiry.getIsPrivated())
                .createdAt(savedInquiry.getCreatedAt())
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
                        .title(inquiry.getTitle())
                        .content(inquiry.getContent())
                        .response(inquiry.getResponse())
                        .isPrivated(inquiry.getIsPrivated())
                        .createdAt(inquiry.getCreatedAt())
                        .updatedAt(inquiry.getUpdatedAt())
                        .build()
                ).collect(Collectors.toList());

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, response).getBody();
    }

    @Override
    public ResponseDto<List<AllInquiryResponseDto>> getAllInquiry() {
        List<AllInquiryResponseDto> response = null;

        List<Inquiry> inquiries = inquiryRepository.findAll();

        response = inquiries.stream()
                .map(inquiry -> AllInquiryResponseDto.builder()
                        .id(inquiry.getInquiryId())
                        .username(inquiry.getUser().getName())
                        .title(inquiry.getTitle())
                        .isPrivated(inquiry.getIsPrivated())
                        .createdAt(inquiry.getCreatedAt())
                        .updatedAt(inquiry.getUpdatedAt())
                        .build()).collect(Collectors.toList());

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, response).getBody();
    }

    @Override
    public ResponseDto<InquiryByIdResponseDto> getInquiryDetail(String username, Long id) {
        InquiryByIdResponseDto response = null;

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));

        String roleName = user.getRole().getRoleName();

        if(inquiry.getIsPrivated()) {
            if(!roleName.equals("TRAINER") && !roleName.equals("ADMIN")) {
                if(!inquiry.getUser().equals(user)) {
                    throw new IllegalArgumentException(ResponseMessage.NO_PERMISSION);
                }
            }
        }

        response = InquiryByIdResponseDto.builder()
                .title(inquiry.getTitle())
                .username(inquiry.getUser().getName())
                .trainerName(inquiry.getTrainer() != null? inquiry.getTrainer().getUser().getName() : null)
                .content(inquiry.getContent())
                .response(inquiry.getResponse())
                .isPrivated(inquiry.getIsPrivated())
                .createdAt(inquiry.getCreatedAt())
                .updatedAt(inquiry.getUpdatedAt())
                .build();

        response = InquiryByIdResponseDto.builder()
                .title(inquiry.getTitle())
                .username(inquiry.getUser().getName())
                .trainerName(inquiry.getTrainer() != null? inquiry.getTrainer().getUser().getName() : null)
                .content(inquiry.getContent())
                .response(inquiry.getResponse())
                .isPrivated(inquiry.getIsPrivated())
                .createdAt(inquiry.getCreatedAt())
                .updatedAt(inquiry.getUpdatedAt())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, response).getBody();
    }

    @Override
    public ResponseDto<InquiryResponseDto> updateInquiry(String username, Long id, InquiryRequestDto dto) {
        InquiryResponseDto response = null;

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));

        if (!inquiry.getUser().equals(user)) {
            throw new IllegalArgumentException(ResponseMessage.NO_PERMISSION);
        }

        inquiry.setTitle(dto.getTitle());
        inquiry.setContent(dto.getContent());
        inquiry.setIsPrivated(dto.getIsPrivated());

        Inquiry savedInquiry = inquiryRepository.save(inquiry);

        response = InquiryResponseDto.builder()
                .title(savedInquiry.getTitle())
                .content(savedInquiry.getContent())
                .isPrivated(savedInquiry.getIsPrivated())
                .createdAt(savedInquiry.getCreatedAt())
                .updatedAt(savedInquiry.getUpdatedAt())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, response).getBody();
    }



    @Override
    public ResponseDto<?> deleteInquiry(String username, Long id) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));

        String role = user.getRole().getRoleName();

        if(!inquiry.getUser().equals(user) && role.equals("ADMIN")) {
            throw new IllegalArgumentException(ResponseMessage.NO_PERMISSION);
        }

        inquiryRepository.delete(inquiry);

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS).getBody();
    }

    @Override
    public ResponseDto<InquiryByIdResponseDto> inquiryAnswer(String username, Long id, InquiryAnswerRequestDto dto) {
        InquiryByIdResponseDto response = null;

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));

        String role = user.getRole().getRoleName();

        if (!role.equals("TRAINER") && !role.equals("ADMIN")) {
            throw new IllegalArgumentException(ResponseMessage.NO_PERMISSION);
        }

        TrainerProfile trainer = trainerProfileRepository.findByUser(user);

        inquiry.setTrainer(trainer);
        inquiry.setResponse(dto.getResponse());

        inquiryRepository.save(inquiry);

        response = InquiryByIdResponseDto.builder()
                .title(inquiry.getTitle())
                .username(inquiry.getUser().getName())
                .trainerName(inquiry.getTrainer().getUser().getName())
                .content(inquiry.getContent())
                .response(inquiry.getResponse())
                .isPrivated(inquiry.getIsPrivated())
                .createdAt(inquiry.getCreatedAt())
                .updatedAt(inquiry.getUpdatedAt())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, response).getBody();
    }
}