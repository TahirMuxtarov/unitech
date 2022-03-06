package com.unitech.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterRequest {

    @NotBlank
    private String pin;
    @NotBlank
    private String password;

    private String firstName;
    private String lastName;

}
