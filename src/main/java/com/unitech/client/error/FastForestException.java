package com.unitech.client.error;

import feign.error.FeignExceptionConstructor;
import feign.error.ResponseBody;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FastForestException extends RuntimeException {

    private final FastErrorResponse errorResponse;

    @FeignExceptionConstructor
    public FastForestException(@ResponseBody FastErrorResponse errorResponse) {
        this.errorResponse = errorResponse;

    }
}
