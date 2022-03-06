package com.unitech.model.dto;

import com.unitech.model.response.CommonResponse;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class TransferResponseDto extends CommonResponse {

    private String debitAccountNo;
    private String creditAccountNo;
    private BigDecimal amount;
    private String transferId;
}
