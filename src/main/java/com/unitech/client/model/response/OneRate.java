package com.unitech.client.model.response;

import lombok.Data;

@Data
public class OneRate {

    private String base;
    private Results result;
    private String updated;
    private Integer ms;
}
