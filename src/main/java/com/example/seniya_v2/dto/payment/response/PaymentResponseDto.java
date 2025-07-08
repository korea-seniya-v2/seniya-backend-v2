package com.example.seniya_v2.dto.payment.response;

import com.example.seniya_v2.common.enums.payment.Status;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDto {
    private Long paymentId;
    private Status status;
}
