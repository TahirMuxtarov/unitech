package com.unitech.error;

import com.unitech.util.ErrorMessage;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{

    private String code;
    private String message;

    public CommonException(String code, String message) {
        System.err.println("common exception all args constructor called");
        this.code = code;
        this.message = message;
    }

    public CommonException(ErrorMessage errorMessage) {
        System.err.println("common exception ErrorMessage constructor called");
        this.message = errorMessage.getMessage();
    }
}
