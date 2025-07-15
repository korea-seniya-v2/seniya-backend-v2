package com.example.seniya_v2.controller.user;

import com.example.seniya_v2.common.constants.ApiMappingPattern;
import com.example.seniya_v2.common.constants.ResponseCode;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.user.request.EmailSendRequestDto;
import com.example.seniya_v2.dto.user.request.UserPasswordResetRequestDto;
import com.example.seniya_v2.dto.user.request.UserSignInRequestDto;
import com.example.seniya_v2.dto.user.request.UserSignUpRequestDto;
import com.example.seniya_v2.dto.user.response.UserSignInResponseDto;
import com.example.seniya_v2.dto.user.response.UserSignUpResponseDto;
import com.example.seniya_v2.provider.JwtTokenProvider;
import com.example.seniya_v2.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ApiMappingPattern.AUTH_API)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider JwtTokenProvider;

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


    @GetMapping("/check-username")
    public ResponseEntity<ResponseDto<Boolean>> checkUsername(@RequestParam String username) {
        boolean available = !authService.existsByUsername(username);
        String message = available ? "사용 가능한 아이디 입니다." : "이미 존재하는 아이디입니다.";
        return ResponseEntity.ok(ResponseDto.success(ResponseCode.SUCCESS, message, available).getBody());
    }

    @GetMapping("/check-email")
    public ResponseEntity<ResponseDto<Boolean>> checkEmail(@RequestParam String email) {
        boolean available = !authService.existsByEmail(email);
        String message = available ? "사용 가능한 이메일 주소 입니다." : "이미 존재하는 이메일 주소입니다.";
        return ResponseEntity.ok(ResponseDto.success(ResponseCode.SUCCESS, message, available).getBody());
    }

}
