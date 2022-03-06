package com.unitech.controller;

import com.unitech.model.response.UserAccountsResponse;
import com.unitech.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public UserAccountsResponse getUserAccounts(@RequestParam String pin) {
        return accountService.getAccounts(pin);
    }

    @GetMapping("/test")
    public String getTest() {
        return "Success";
    }
}
