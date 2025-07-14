package com.example.seniya_v2.service.implementations;

import com.example.seniya_v2.common.constants.ResponseCode;
import com.example.seniya_v2.common.constants.ResponseMessage;
import com.example.seniya_v2.common.enums.payment.Status;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.pass.response.PassResponseDto;
import com.example.seniya_v2.entity.Pass;
import com.example.seniya_v2.repository.PassRepository;
import com.example.seniya_v2.service.PassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassServiceImpl implements PassService {
    private final PassRepository passRepository;

    @Override
    public ResponseDto<List<PassResponseDto>> getMyValidPasses(String username) {
        List<Pass> passes = passRepository.findAllByUserUsernameAndPaymentStatus(username, Status.SUCCESS);
        List<PassResponseDto> responseDtos = passes.stream()
                .map(PassResponseDto::fromEntity)
                .toList();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, responseDtos).getBody();
    }
}
