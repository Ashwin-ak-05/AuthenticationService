package com.example.AuthUserService.models;

import com.example.AuthUserService.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class User extends BaseModel{
    public String name;
    public String email;
    public String hashedPassword;

    @Enumerated
    @ManyToMany(fetch = FetchType.EAGER)
    public List<Role> roles;
    public UserStatus userStatus;
}
