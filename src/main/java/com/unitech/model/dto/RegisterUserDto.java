package com.unitech.model.dto;

import lombok.Data;

@Data
public class RegisterUserDto {

    private String pin;
    private String password;
    private String firstName;
    private String lastName;

}
