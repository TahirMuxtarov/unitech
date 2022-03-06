package com.unitech.service;

import com.unitech.client.model.response.OneRate;
import com.unitech.client.model.response.Results;
import com.unitech.error.CommonException;
import com.unitech.mapper.TransferMapper;
import com.unitech.model.dto.TransferRequestDto;
import com.unitech.model.entity.Accounts;
import com.unitech.model.entity.Payments;
import com.unitech.model.request.TransferRequest;
import com.unitech.model.response.CommonResponse;
import com.unitech.model.response.TransferResponse;
import com.unitech.model.response.UserAccountsResponse;
import com.unitech.repository.PaymentsRepository;
import com.unitech.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferService {

    private final AccountService accountService;
    private final PaymentsRepository paymentsRepository;
    private final TransferMapper transferMapper;

    @Transactional
    public TransferResponse transferToAccount(TransferRequest request) {
        TransferRequestDto transferRequestDto = transferMapper.toTransferRequestDto(request);
        if (transferRequestDto.getDebitAccountNo().equals(transferRequestDto.getCreditAccountNo())) {
            throw new CommonException(ErrorMessage.ACCOUNT_MUST_DIFFER);
        }
        Accounts creditAccount = accountService.getProperAccount(transferRequestDto.getCreditAccountNo());

        UserAccountsResponse myAccounts = accountService.getAccounts(transferRequestDto.getPin());
        Accounts debitAccount = myAccounts.getAccounts().stream()
                .filter(account -> account.getAccountNo().equals(transferRequestDto.getDebitAccountNo()))
                .findFirst()
                .orElseThrow(() -> new CommonException(ErrorMessage.ACCOUNT_NOT_FOUND));

        if (debitAccount.getAmount().compareTo(transferRequestDto.getAmount()) < 0) {
            throw new CommonException(ErrorMessage.INSUFFICIENT_BALANCE);
        }
        debitAccount.setAmount(debitAccount.getAmount().subtract(request.getAmount()));
        creditAccount.setAmount(creditAccount.getAmount().add(request.getAmount()));

        Payments payment = Payments.builder()
                .date(new Date())
                .debitAccountNo(transferRequestDto.getDebitAccountNo())
                .creditAccountNo(transferRequestDto.getCreditAccountNo())
                .amount(transferRequestDto.getAmount())
                .paymentId(transferRequestDto.getTransferId())
                .build();
        log.info("Saving payment for transferId: {}", transferRequestDto.getTransferId());
        accountService.save(debitAccount);
        accountService.save(creditAccount);
        paymentsRepository.save(payment);

        return TransferResponse.builder()
                .debitAccountNo(debitAccount.getAccountNo())
                .creditAccountNo(creditAccount.getAccountNo())
                .amount(transferRequestDto.getAmount())
                .transferId(transferRequestDto.getTransferId())
                .build();
    }

}
