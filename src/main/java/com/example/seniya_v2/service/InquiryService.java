package com.example.seniya_v2.service;

import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.inquiry.request.InquiryAnswerRequestDto;
import com.example.seniya_v2.dto.inquiry.request.InquiryRequestDto;
import com.example.seniya_v2.dto.inquiry.response.AllInquiryResponseDto;
import com.example.seniya_v2.dto.inquiry.response.InquiryByIdResponseDto;
import com.example.seniya_v2.dto.inquiry.response.InquiryResponseDto;
import com.example.seniya_v2.dto.inquiry.response.MyInquiryResponseDto;
import jakarta.validation.Valid;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface InquiryService {
    ResponseDto<InquiryResponseDto> createInquiry(String username, @Valid InquiryRequestDto dto);

    ResponseDto<List<MyInquiryResponseDto>> getMyInquiry(String username);

    ResponseDto<List<AllInquiryResponseDto>> getAllInquiry();

    ResponseDto<InquiryByIdResponseDto> getInquiryDetail(String username, Long id)throws AccessDeniedException;

    ResponseDto<InquiryResponseDto> updateInquiry(String username, Long id, @Valid InquiryRequestDto dto);

    ResponseDto<?> deleteInquiry(String username, Long id);

    ResponseDto<InquiryByIdResponseDto> inquiryAnswer(String username, Long id, @Valid InquiryAnswerRequestDto dto);
}

