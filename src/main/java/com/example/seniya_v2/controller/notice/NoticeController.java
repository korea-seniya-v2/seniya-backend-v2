package com.example.seniya_v2.controller.notice;

import com.example.seniya_v2.common.constants.ApiMappingPattern;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.notice.request.NoticeCreateRequestDto;
import com.example.seniya_v2.dto.notice.request.NoticeUpdateRequestDto;
import com.example.seniya_v2.dto.notice.response.GetNoticeDetailResponseDto;
import com.example.seniya_v2.dto.notice.response.NoticeListResponseDto;
import com.example.seniya_v2.dto.notice.response.NoticeResponseDto;
import com.example.seniya_v2.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.NOTICE_API)
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<ResponseDto<NoticeResponseDto>> createNotice(@Valid @RequestBody NoticeCreateRequestDto dto) {
        ResponseDto<NoticeResponseDto> response = noticeService.createNotice(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<GetNoticeDetailResponseDto>> updateNotice(
            @PathVariable Long id,
            @Valid @RequestBody NoticeUpdateRequestDto dto
            ) {
        ResponseDto<GetNoticeDetailResponseDto> response = noticeService.updateNotice(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> deleteNotice(
            @PathVariable Long id
    ) {
        noticeService.deleteNotice(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<NoticeListResponseDto>>> getAllNotices() {
        ResponseDto<List<NoticeListResponseDto>> response = noticeService.getAllNotices();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<GetNoticeDetailResponseDto>> getNoticeById(@PathVariable Long id) {
        ResponseDto<GetNoticeDetailResponseDto> response = noticeService.getNoticeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/top")
    public ResponseEntity<ResponseDto<List<NoticeListResponseDto>>> getTopNotices(@RequestParam(defaultValue = "5") int limit) {
        ResponseDto<List<NoticeListResponseDto>> response = noticeService.getTopNotices(limit);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
