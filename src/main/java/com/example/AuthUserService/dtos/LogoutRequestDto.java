package com.example.AuthUserService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestDto {
    public long userId;
    public String tokenValue;
}
