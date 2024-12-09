package com.example.AuthUserService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    public String username;
    public String email;
    public String password;
}
