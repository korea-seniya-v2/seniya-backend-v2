package com.example.seniya_v2.service.implementations;

import com.example.seniya_v2.common.constants.ResponseCode;
import com.example.seniya_v2.common.constants.ResponseMessage;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.user.request.MyInfoUpdateRequestDto;
import com.example.seniya_v2.dto.user.request.UserSignInRequestDto;
import com.example.seniya_v2.dto.user.response.GetMyInfoResponseDto;
import com.example.seniya_v2.entity.User;
import com.example.seniya_v2.repository.UserRepository;
import com.example.seniya_v2.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseDto<GetMyInfoResponseDto> getUserInfo(String username) {
        GetMyInfoResponseDto dto = null;

        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        dto = GetMyInfoResponseDto.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .phone(user.getPhone())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt().toLocalDate())
                .updatedAt(user.getUpdatedAt().toLocalDate())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, dto).getBody();
    }

    @Override
    @Transactional
    public ResponseDto<GetMyInfoResponseDto> updateUserInfo(String username, MyInfoUpdateRequestDto dto) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        if(dto.getUsername() != null && !user.getUsername().equals(dto.getUsername())){
            userRepository.findByUsername(dto.getUsername())
                    .ifPresent(existingUser -> {
                        throw new IllegalArgumentException("이미 사용 중인 아이디 입니다.");
                    });
            user.setUsername(dto.getUsername());
        }

        if (dto.getPhone() != null && !user.getPhone().equals(dto.getPhone())){
            userRepository.findByPhone(dto.getPhone())
                    .ifPresent(existingUser -> {
                        throw new IllegalArgumentException("이미 사용 중인 전화번호 입니다.");
                    });
            user.setPhone(dto.getPhone());
        }

        if (dto.getEmail() != null && !user.getEmail().equals(dto.getEmail())){
            userRepository.findByEmail(dto.getEmail())
                    .ifPresent(existingUser -> {
                        throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
                    });
            user.setEmail(dto.getEmail());
        }

        GetMyInfoResponseDto responseDto = GetMyInfoResponseDto.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .phone(user.getPhone())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt().toLocalDate())
                .updatedAt(user.getUpdatedAt().toLocalDate())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, responseDto).getBody();
    }

    @Override
    @Transactional
    public ResponseDto<?> deleteUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));
        userRepository.delete(user);

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, null).getBody();
    }

}
