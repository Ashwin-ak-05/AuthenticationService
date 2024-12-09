package com.example.AuthUserService.dtos;

import com.example.AuthUserService.dtos.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginResponseDto {
    public String tokenValue;
    public ResponseStatus responseStatus;
}
