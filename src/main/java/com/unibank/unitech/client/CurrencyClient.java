package com.unibank.unitech.client;

import com.unibank.unitech.client.model.CurrencyRateDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-api-client", url = "https://api.currency.com")
public interface CurrencyClient {

    @GetMapping("/{currency}/rates")
    List<CurrencyRateDto> getCurrencies(@PathVariable(value = "currency") String baseCurrency);
}
