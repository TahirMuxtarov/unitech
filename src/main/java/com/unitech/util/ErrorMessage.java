package com.unitech.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    NOT_FOUND( "Not found"),
    ACCOUNT_NOT_FOUND("Account not found"),
    ACCOUNT_MUST_DIFFER("debit and credit accound should be different"),
    INSUFFICIENT_BALANCE("Insufficient balance"),
    USER_ALREADY_EXIST("User already exist"),
    USER_NOT_FOUND("User not found"),
    INVALID_CREDENTIALS("Invalid pin or password"),
    INVALID_PASSWORD("Invalid password"),
    ACCOUNT_NOT_ACTIVE("Account is not active");
    private String message;

}
