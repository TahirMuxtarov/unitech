package com.unitech.model.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransferRequestDto {

    private String pin;
    private String debitAccountNo;
    private String creditAccountNo;
    private BigDecimal amount;
    private String transferId;
}
