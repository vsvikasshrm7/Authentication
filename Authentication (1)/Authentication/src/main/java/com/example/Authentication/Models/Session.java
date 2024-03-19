package com.example.Authentication.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Session extends BaseClass{

    private Date expiringAt;
    private String token;
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;
    @OneToOne
    private  User user;
}
