package com.unitech.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {

    @NotNull
    private String pin;
    @NotNull
    private String password;
}
