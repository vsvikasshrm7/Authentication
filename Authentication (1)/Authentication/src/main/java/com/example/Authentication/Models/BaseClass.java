package com.example.Authentication.Models;

import jakarta.annotation.Generated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public class BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
}
