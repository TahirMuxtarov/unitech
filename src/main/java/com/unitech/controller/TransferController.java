package com.unitech.controller;

import com.unitech.model.request.TransferRequest;
import com.unitech.model.response.TransferResponse;
import com.unitech.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping("/toAccount")
    public TransferResponse transferToAccount(@RequestBody @Valid TransferRequest request) {
        return transferService.transferToAccount(request);
    }
}
