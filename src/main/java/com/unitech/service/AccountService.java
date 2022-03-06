package com.unitech.service;

import com.unitech.error.CommonException;
import com.unitech.model.AccountStatus;
import com.unitech.model.entity.Accounts;
import com.unitech.model.entity.User;
import com.unitech.model.response.UserAccountsResponse;
import com.unitech.repository.AccountRepository;
import com.unitech.repository.AuthRepository;
import com.unitech.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {


    private final AccountRepository accountRepository;
    private final AuthRepository authRepository;

    public UserAccountsResponse getAccounts(String pin) {
        UserAccountsResponse response = new UserAccountsResponse();
        User byPin = authRepository.findByPin(pin).orElse(null);

        if (Objects.isNull(byPin)) {
            throw new CommonException(ErrorMessage.USER_NOT_FOUND);
        }
        List<Accounts> accounts = accountRepository.findAllByUser(byPin)
                .orElseThrow(() -> new CommonException(ErrorMessage.ACCOUNT_NOT_FOUND));

        List<Accounts> activeStatus = accounts.stream()
                .filter(account -> account.getAccountStatus().equals(AccountStatus.ACTIVE))
                .collect(Collectors.toList());

        response.setAccounts(activeStatus);

        return response;
    }

    public void save(Accounts accounts) {
        accountRepository.save(accounts);
    }

    public Accounts getProperAccount(String accountNo) {
        return accountRepository.findByAccountNo(accountNo)
                .orElseThrow(() -> new CommonException(ErrorMessage.ACCOUNT_NOT_FOUND));
    }


}
