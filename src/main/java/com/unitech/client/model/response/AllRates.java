package com.unitech.client.model.response;

import lombok.Data;

@Data
public class AllRates {

    private String base;
    private Results results;
    private String updated;
    private Integer ms;
}
