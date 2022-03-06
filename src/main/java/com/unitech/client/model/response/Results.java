package com.unitech.client.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Results {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "AED")
    private String aed;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "ANG")
    private String ang;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "ARS")
    private String ars;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "AZN")
    private String azn;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "CNY")
    private String cny;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "CZK")
    private String czk;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "EUR")
    private String eur;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "GBP")
    private String gbp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "IDR")
    private String idr;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "IRR")
    private String irr;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "JPY")
    private String jpy;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "KZT")
    private String kzt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "PEN")
    private String pen;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "RUB")
    private String rub;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "USD")
    private String usd;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "XDR")
    private String xdr;

}
