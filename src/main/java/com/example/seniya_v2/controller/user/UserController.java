package com.example.seniya_v2.controller.user;

import com.example.seniya_v2.common.constants.ApiMappingPattern;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.user.request.MyInfoUpdateRequestDto;
import com.example.seniya_v2.dto.user.response.GetMyInfoResponseDto;
import com.example.seniya_v2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.USER_API)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ResponseDto<GetMyInfoResponseDto>> getUserInfo(@AuthenticationPrincipal String username) {
        ResponseDto<GetMyInfoResponseDto> response = userService.getUserInfo(username);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/me")
    public ResponseEntity<ResponseDto<GetMyInfoResponseDto>> updateUserInfo(@AuthenticationPrincipal String username, @Valid @RequestBody MyInfoUpdateRequestDto dto) {
        ResponseDto<GetMyInfoResponseDto> response = userService.updateUserInfo(username, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/me")
    public ResponseEntity<ResponseDto<?>> deleteUser(@AuthenticationPrincipal String username) {
        ResponseDto<?> response = userService.deleteUser(username);
        return ResponseEntity.ok(response);
    }


}
