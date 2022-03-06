package com.unitech.controller;

import com.unitech.client.model.response.AllRates;
import com.unitech.client.model.response.OneRate;
import com.unitech.service.FastForestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rate")
public class RateController {

    private final FastForestService fastForestService;

    @GetMapping("/all")
    public AllRates getAllRates(@RequestParam String from) {
        return fastForestService.getAllRates(from);
    }

    @GetMapping("/one")
    public OneRate getOneRate(@RequestParam String from,
                              @RequestParam String to) {
        return fastForestService.getOneRate(from, to);
    }
}
