package com.example.seniya_v2.service;

import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.payment.request.ConfirmPaymentRequestDto;
import com.example.seniya_v2.dto.payment.request.CreatePaymentRequestDto;
import com.example.seniya_v2.dto.payment.response.GetAllPaymentResponseDto;
import com.example.seniya_v2.dto.payment.response.PaymentResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface PaymentService {
    ResponseDto<PaymentResponseDto> createPayment(String username, @Valid CreatePaymentRequestDto dto);

    ResponseDto<List<GetAllPaymentResponseDto>> getAllPayments();

    ResponseDto<PaymentResponseDto> confirmPayment(long id, ConfirmPaymentRequestDto dto);
}
