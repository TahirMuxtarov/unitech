package com.unitech.repository;

import com.unitech.client.FastForestClient;
import com.unitech.client.model.response.AllRates;
import com.unitech.client.model.response.OneRate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FastForestRepository {

    @Value(value = "${application.service.fast-forest.api-key}")
    private String apiKey;

    private final FastForestClient client;

    public AllRates getAllRates(String baseCurrency) {
        return client.getAllRates(baseCurrency, apiKey);
    }

    public OneRate getOneRate(String baseCurrency, String value) {
        return client.getOneRate(baseCurrency, value, apiKey);
    }
}
