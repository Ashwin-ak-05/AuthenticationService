package com.example.AuthUserService.dtos;


import com.example.AuthUserService.dtos.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private ResponseStatus responseStatus;
    private String message;
}
