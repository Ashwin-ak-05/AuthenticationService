package com.example.AuthUserService.controllers;

import com.example.AuthUserService.dtos.*;
import com.example.AuthUserService.dtos.enums.ResponseStatus;
import com.example.AuthUserService.dtos.enums.TokenVerificationStatus;
import com.example.AuthUserService.exceptions.InvalidTokenArgument;
import com.example.AuthUserService.exceptions.TokenAlreadyExpired;
import com.example.AuthUserService.models.Token;
import com.example.AuthUserService.models.User;
import com.example.AuthUserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController  {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long userId){
        User user = userService.getUserById(userId);
        return new UserDto(user);
    }

    @PostMapping("/signup")
    public SignUpResponseDto signUp(@RequestBody SignUpRequestDto requestDto){
        User user = userService.signUp(requestDto.getUsername(),requestDto.getEmail(),requestDto.getPassword());
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        signUpResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        signUpResponseDto.setMessage("User "+user.getName()+" signed up successfully");
        return signUpResponseDto;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto){
        Token token = userService.login(requestDto.getEmail(),requestDto.getPassword());
        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setTokenValue(token.getValue());
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDto;
    }

    @PostMapping("/logout")
    public LogoutResponseDto logout(@RequestBody LogoutRequestDto requestDto){
        Token deletedToken = userService.logout(requestDto.getUserId(), requestDto.getTokenValue());
        LogoutResponseDto responseDTO = new LogoutResponseDto();
        responseDTO.setTokenHasBeenDeleted(deletedToken.isDeleted());
        responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDTO;
    }

    @PostMapping("/validateToken")
    public ValidateTokenResponseDto validateToken(@RequestBody ValidateTokenRequestDto requestDTO) throws TokenAlreadyExpired, InvalidTokenArgument {
        Boolean isValid = userService.validateToken(requestDTO.getTokenValue(), requestDTO.getUserId());
        ValidateTokenResponseDto responseDTO = new ValidateTokenResponseDto();
        responseDTO.setTokenVerificationStatus(isValid == true ? TokenVerificationStatus.VERIFIED : TokenVerificationStatus.MALICIOUS);
        responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDTO;
    }
}
