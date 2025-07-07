package com.example.seniya_v2.dto.payment.request;

import com.example.seniya_v2.common.enums.payment.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePaymentRequestDto {
    private int couponCount;
    private Method method;
}
