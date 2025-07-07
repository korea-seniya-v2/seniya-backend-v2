package com.example.seniya_v2.dto.user.response;

import com.example.seniya_v2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserSignUpResponseDto {
    User user;
}
