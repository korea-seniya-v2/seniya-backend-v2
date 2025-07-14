package com.example.seniya_v2.service.implementations;

import com.example.seniya_v2.common.constants.ResponseCode;
import com.example.seniya_v2.common.constants.ResponseMessage;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.user.request.UserSignInRequestDto;
import com.example.seniya_v2.dto.user.request.UserSignUpRequestDto;
import com.example.seniya_v2.dto.user.response.UserResponseDto;
import com.example.seniya_v2.dto.user.response.UserSignInResponseDto;
import com.example.seniya_v2.dto.user.response.UserSignUpResponseDto;
import com.example.seniya_v2.entity.Role;
import com.example.seniya_v2.entity.User;
import com.example.seniya_v2.provider.JwtTokenProvider;
import com.example.seniya_v2.repository.RoleRepository;
import com.example.seniya_v2.repository.UserRepository;
import com.example.seniya_v2.service.AuthService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseDto<UserSignUpResponseDto> signup(UserSignUpRequestDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();
        String name = dto.getName();
        String email = dto.getEmail();
        String phone = dto.getPhone();

        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException(ResponseCode.FAIL);
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new DuplicateFormatFlagsException(ResponseCode.FAIL);
        }

        Role userRole = roleRepository.findByRoleName("USER")
                .orElseGet(() -> roleRepository.save(Role.builder().roleName("USER").build()));

        String encodePassword = bCryptPasswordEncoder.encode(password);

        User user = User.builder()
                .username(username)
                .password(encodePassword)
                .email(email)
                .name(name)
                .phone(phone)
                .role(userRole)
                .emailVerified(false)
                .build();

        userRepository.save(user);

        UserSignUpResponseDto data = new UserSignUpResponseDto();

        return ResponseDto.success(ResponseCode.SUCCESS, "회원 가입이 완료되었습니다.", data).getBody();
    }

    @Override
    public ResponseDto<UserSignInResponseDto> login(UserSignInRequestDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(ResponseCode.USER_NOT_FOUND));

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException(ResponseCode.FAIL);
        }

        UserResponseDto responseDto = new UserResponseDto(
                user.getUserId(), user.getRole().getRoleName(), user.getName()
        );

        String token = jwtTokenProvider.generateToken(user.getUsername(), user.getRole().getRoleName());
        int exprTime = jwtTokenProvider.getExpiration();
        int roleId = user.getRole().getRoleId();
        UserSignInResponseDto data = new UserSignInResponseDto(token, responseDto, exprTime, roleId);

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, data).getBody();
    }

    @Override
    public ResponseDto<?> logout(String username) {
        return ResponseDto.success(ResponseCode.SUCCESS, "로그아웃 처리 완료").getBody();
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
