package com.example.seniya_v2.controller.user;

import com.example.seniya_v2.common.constants.ApiMappingPattern;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.user.request.UserSignInRequestDto;
import com.example.seniya_v2.dto.user.request.UserSignUpRequestDto;
import com.example.seniya_v2.dto.user.response.UserSignInResponseDto;
import com.example.seniya_v2.dto.user.response.UserSignUpResponseDto;
import com.example.seniya_v2.provider.JwtTokenProvider;
import com.example.seniya_v2.service.AuthService;
import com.example.seniya_v2.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.AUTH_API)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MailService mailService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<UserSignUpResponseDto>> signup(@Valid @RequestBody UserSignUpRequestDto dto) {
        ResponseDto<UserSignUpResponseDto> response = authService.signup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseDto<UserSignInResponseDto>> login(@Valid @RequestBody UserSignInRequestDto dto) {
        ResponseDto<UserSignInResponseDto> response = authService.login(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseDto<?>> logout(@AuthenticationPrincipal String username) {
        ResponseDto<?> response = authService.logout(username);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
