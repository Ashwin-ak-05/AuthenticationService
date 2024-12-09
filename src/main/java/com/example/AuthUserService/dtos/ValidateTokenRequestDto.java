package com.example.AuthUserService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequestDto {
    public String tokenValue;
    public Long userId;
}
