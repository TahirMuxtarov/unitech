package com.unitech.service;

import com.unitech.client.model.response.AllRates;
import com.unitech.client.model.response.MultiRate;
import com.unitech.client.model.response.OneRate;
import com.unitech.repository.FastForestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FastForestService {

    private final FastForestRepository fastForestRepository;

    public AllRates getAllRates(String baseCurrency) {
        return fastForestRepository.getAllRates(baseCurrency);
    }

    public OneRate getOneRate(String baseCurr, String valCurr) {
       return fastForestRepository.getOneRate(baseCurr, valCurr);
    }

    public MultiRate getMultiRate(String from, String[] to) {
        return fastForestRepository.getMultiRate(from,to);
    }
}
