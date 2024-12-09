package com.example.AuthUserService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    public String email;
    public String password;
}
