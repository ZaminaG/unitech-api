package com.unibank.unitech.client.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyRateDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String currencyCode;
    private String currencyName;
    private Float currencyRate;
}
