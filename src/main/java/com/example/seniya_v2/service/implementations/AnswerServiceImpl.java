package com.example.seniya_v2.service.implementations;

import com.example.seniya_v2.common.constants.ResponseCode;
import com.example.seniya_v2.common.constants.ResponseMessage;
import com.example.seniya_v2.common.enums.InquiryStatus;
import com.example.seniya_v2.common.enums.uploadFile.TargetType;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.answer.request.AnswerCreateRequestDto;
import com.example.seniya_v2.dto.answer.response.AnswerResponseDto;
import com.example.seniya_v2.entity.Answer;
import com.example.seniya_v2.entity.Inquiry;
import com.example.seniya_v2.entity.UploadFile;
import com.example.seniya_v2.entity.User;
import com.example.seniya_v2.repository.AnswerRepository;
import com.example.seniya_v2.repository.InquiryRepository;
import com.example.seniya_v2.repository.UploadFileRepository;
import com.example.seniya_v2.repository.UserRepository;
import com.example.seniya_v2.service.AnswerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final UserRepository userRepository;
    private final InquiryRepository inquiryRepository;
    private final AnswerRepository answerRepository;
    private final UploadFileRepository fileRepository;

    @Override
    public ResponseDto<AnswerResponseDto> createAnswer(Long id, String username, AnswerCreateRequestDto dto) {
        AnswerResponseDto response = null;

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));

        Answer newAnswer = Answer.builder()
                .user(user)
                .inquiry(inquiry)
                .content(dto.getContent())
                .build();

        inquiry.setStatus(InquiryStatus.COMPLETED);
        answerRepository.save(newAnswer);

        List<UploadFile> inquiryImage = fileRepository.findByTargetIdAndTargetType(inquiry.getInquiryId(), TargetType.INQUIRY);
        List<String> imageUrl = inquiryImage.stream()
                .map(UploadFile::getFilePath)
                .collect(Collectors.toList());



        response = AnswerResponseDto.builder()
                .userName(inquiry.getUser().getName())
                .title(inquiry.getTitle())
                .inquiryContent(inquiry.getContent())
                .inquiryImageUrl(imageUrl)
                .admin(newAnswer.getUser().getName())
                .answerContent(newAnswer.getContent())
                .status(newAnswer.getInquiry().getStatus())
                .createdAt(newAnswer.getInquiry().getCreatedAt())
                .updatedAt(newAnswer.getInquiry().getUpdatedAt())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, response).getBody();
    }
}
