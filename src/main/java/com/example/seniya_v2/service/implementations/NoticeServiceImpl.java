package com.example.seniya_v2.service.implementations;

import com.example.seniya_v2.common.constants.ResponseCode;
import com.example.seniya_v2.common.constants.ResponseMessage;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.notice.request.NoticeCreateRequestDto;
import com.example.seniya_v2.dto.notice.request.NoticeUpdateRequestDto;
import com.example.seniya_v2.dto.notice.response.GetNoticeDetailResponseDto;
import com.example.seniya_v2.dto.notice.response.NoticeListResponseDto;
import com.example.seniya_v2.dto.notice.response.NoticeResponseDto;
import com.example.seniya_v2.entity.Notice;
import com.example.seniya_v2.entity.User;
import com.example.seniya_v2.repository.NoticeRepository;
import com.example.seniya_v2.repository.UserRepository;
import com.example.seniya_v2.service.NoticeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<NoticeResponseDto> createNotice(NoticeCreateRequestDto dto) {
        User adminUser = userRepository.findByRole_RoleName("ADMIN")
                .orElseThrow(() -> new EntityNotFoundException("Admin user not found"));

        Notice notice = Notice.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(adminUser)
                .build();

        noticeRepository.save(notice);

        NoticeResponseDto responseDto = NoticeResponseDto.builder()
                .username(notice.getUser().getName())
                .noticeId(notice.getNoticeId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdAt(notice.getCreatedAt())
                .updatedAt(notice.getUpdatedAt())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, responseDto).getBody();
    }


    @Override
    public ResponseDto<GetNoticeDetailResponseDto> updateNotice(Long id, NoticeUpdateRequestDto dto) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NOTICE NOT FOUND"));

        if (dto.getTitle() != null) {
            notice.setTitle(dto.getTitle());
        }
        if (dto.getContent() != null) {
            notice.setContent(dto.getContent());
        }

        Notice updatedNotice = noticeRepository.save(notice);

        GetNoticeDetailResponseDto responseDto = GetNoticeDetailResponseDto.builder()
                .username(updatedNotice.getUser().getName())
                .noticeId(updatedNotice.getNoticeId())
                .title(updatedNotice.getTitle())
                .content(updatedNotice.getContent())
                .createdAt(updatedNotice.getCreatedAt())
                .updatedAt(updatedNotice.getUpdatedAt())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, responseDto).getBody();
    }


    @Override
    public ResponseDto<?> deleteNotice(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NOTICE NOT FOUND"));

        noticeRepository.delete(notice);

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS).getBody();
    }
    @Override // 전체 조회
    public ResponseDto<List<NoticeListResponseDto>> getAllNotices() {
        List<NoticeListResponseDto> responseDtos = null;

        List<Notice> notices = noticeRepository.findAll();

        responseDtos = notices.stream()
                .map(notice -> NoticeListResponseDto.builder()
                        .username(notice.getUser().getName())
                        .noticeId(notice.getNoticeId())
                        .title(notice.getTitle())
                        .createdAt(notice.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, responseDtos).getBody();
    }

    @Override
    public ResponseDto<GetNoticeDetailResponseDto> getNoticeById(Long id){
        GetNoticeDetailResponseDto responseDto = null;

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.FILE_NOT_FOUND + id));

        responseDto = GetNoticeDetailResponseDto.builder()
                .username(notice.getUser().getName())
                .noticeId(notice.getNoticeId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdAt(notice.getCreatedAt())
                .updatedAt(notice.getUpdatedAt())
                .build();
        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, responseDto).getBody();
    }
    @Override
    public ResponseDto<List<NoticeListResponseDto>> getTopNotices(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Notice> notices = noticeRepository.findNoticesWithPriority(pageable);

        List<NoticeListResponseDto> responseDtos = notices.stream()
                .map(notice -> NoticeListResponseDto.builder()
                        .username(notice.getUser().getName())
                        .noticeId(notice.getNoticeId())
                        .title(notice.getTitle())
                        .createdAt(notice.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, responseDtos).getBody();
    }

}
