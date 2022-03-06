package com.unitech.error;

import com.unitech.util.ErrorMessage;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{

    private String code;
    private String message;

    public CommonException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonException(ErrorMessage errorMessage) {
        this.message = errorMessage.getMessage();
    }
}
