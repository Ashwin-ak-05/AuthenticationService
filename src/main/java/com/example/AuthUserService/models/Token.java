package com.example.AuthUserService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
public class Token extends BaseModel {
    public String value;

    @ManyToOne
    public User user;
    public Date expiryDate;
}
