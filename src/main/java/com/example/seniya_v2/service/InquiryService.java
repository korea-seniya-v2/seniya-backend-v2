package com.example.seniya_v2.service;

import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.inquiry.request.InquiryRequestDto;
import com.example.seniya_v2.dto.inquiry.response.AllInquiryResponseDto;
import com.example.seniya_v2.dto.inquiry.response.InquiryByIdResponseDto;
import com.example.seniya_v2.dto.inquiry.response.InquiryResponseDto;
import com.example.seniya_v2.dto.inquiry.response.MyInquiryResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface InquiryService {
    ResponseDto<InquiryResponseDto> createInquiry(String username, @Valid InquiryRequestDto dto, List<MultipartFile> files) throws IOException;

    ResponseDto<List<MyInquiryResponseDto>> getMyInquiry(String username);

    ResponseDto<List<AllInquiryResponseDto>> getAllInquiry(String username);

    ResponseDto<InquiryByIdResponseDto> getInquiryDetail(String username, Long id);

    ResponseDto<?> deleteInquiry(String username, Long id);

}