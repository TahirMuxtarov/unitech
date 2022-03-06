package com.unitech.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class TransferResponse extends CommonResponse{

    private String debitAccountNo;
    private String creditAccountNo;
    private BigDecimal amount;
    private String transferId;
}
