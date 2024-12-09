package com.example.AuthUserService.dtos;

import com.example.AuthUserService.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    public Long id;
    public String name;

    public UserDto(User user){
        this.id=user.getId();
        this.name=user.getName();
    }
}
