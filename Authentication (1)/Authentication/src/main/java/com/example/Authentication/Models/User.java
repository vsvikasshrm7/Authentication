package com.example.Authentication.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseClass {
    String name;
    String password;
    String email;

    @Enumerated(EnumType.ORDINAL)
    @ManyToMany
    List<Role> role;


}
