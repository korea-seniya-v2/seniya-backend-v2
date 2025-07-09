package com.example.seniya_v2.controller.inquiry;

import com.example.seniya_v2.common.constants.ApiMappingPattern;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.inquiry.request.InquiryAnswerRequestDto;
import com.example.seniya_v2.dto.inquiry.request.InquiryRequestDto;
import com.example.seniya_v2.dto.inquiry.response.AllInquiryResponseDto;
import com.example.seniya_v2.dto.inquiry.response.InquiryByIdResponseDto;
import com.example.seniya_v2.dto.inquiry.response.InquiryResponseDto;
import com.example.seniya_v2.dto.inquiry.response.MyInquiryResponseDto;
import com.example.seniya_v2.service.InquiryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.rtf.RTFEditorKit;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.INQUIRY_API)
public class InquiryController {

    private final InquiryService inquiryService;

    @PostMapping
    public ResponseEntity<ResponseDto<InquiryResponseDto>> createInquiry(
            @AuthenticationPrincipal String username,
            @Valid @RequestBody InquiryRequestDto dto
    ) {
        ResponseDto<InquiryResponseDto> response = inquiryService.createInquiry(username, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseDto<List<MyInquiryResponseDto>>> getMyInquiry(@AuthenticationPrincipal String username) {
        ResponseDto<List<MyInquiryResponseDto>> response = inquiryService.getMyInquiry(username);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<AllInquiryResponseDto>>> getAllInquiry() {
        ResponseDto<List<AllInquiryResponseDto>> response = inquiryService.getAllInquiry();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<InquiryByIdResponseDto>> getInquiryDetail(
            @AuthenticationPrincipal String username,
            @PathVariable Long id
    ) throws AccessDeniedException {
        ResponseDto<InquiryByIdResponseDto> response = inquiryService.getInquiryDetail(username, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<InquiryResponseDto>> updateInquiry(
            @AuthenticationPrincipal String username,
            @PathVariable Long id,
            @Valid @RequestBody InquiryRequestDto dto
    ) {
        ResponseDto<InquiryResponseDto> response = inquiryService.updateInquiry(username, id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> deleteInquiry(
            @AuthenticationPrincipal String username,
            @PathVariable Long id
    ) {
        inquiryService.deleteInquiry(username, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/response")
    public ResponseEntity<ResponseDto<InquiryByIdResponseDto>> inquiryAnswer(
            @AuthenticationPrincipal String username,
            @PathVariable Long id,
            @Valid @RequestBody InquiryAnswerRequestDto dto
    ) {
        ResponseDto<InquiryByIdResponseDto> response = inquiryService.inquiryAnswer(username, id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
