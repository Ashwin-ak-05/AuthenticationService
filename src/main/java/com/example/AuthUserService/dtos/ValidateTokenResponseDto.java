package com.example.AuthUserService.dtos;

import com.example.AuthUserService.dtos.enums.ResponseStatus;
import com.example.AuthUserService.dtos.enums.TokenVerificationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenResponseDto {
    public TokenVerificationStatus tokenVerificationStatus;
    public ResponseStatus responseStatus;
}
