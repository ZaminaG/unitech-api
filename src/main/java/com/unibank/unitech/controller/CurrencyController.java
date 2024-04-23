package com.unibank.unitech.controller;

import com.unibank.unitech.client.model.CurrencyRateDto;
import com.unibank.unitech.service.CurrencyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping("/{currency}/rates")
    public ResponseEntity<List<CurrencyRateDto>> getCurrencies(@PathVariable String currency) {
        return ResponseEntity.ok(currencyService.getCurrencies(currency));
    }
}
