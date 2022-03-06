package com.unitech.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class TransferRequest {

    @NotBlank
    private String pin;
    @NotNull
    private String debitAccountNo;
    @NotNull
    private String creditAccountNo;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private String transferId;
}
