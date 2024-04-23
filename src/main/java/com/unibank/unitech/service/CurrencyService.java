package com.unibank.unitech.service;

import com.unibank.unitech.client.CurrencyClient;
import com.unibank.unitech.client.model.CurrencyRateDto;
import com.unibank.unitech.dao.repository.CurrencyRedisRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyClient currencyClient;
    private final CurrencyRedisRepository redisRepository;

    public List<CurrencyRateDto> getCurrencies(String baseCurrency) {
        var currencyRates = redisRepository.read(baseCurrency);
        if (Objects.nonNull(currencyRates)) {
            System.out.println("read from redis ");
            return currencyRates;
        }
        // currencyRates = currencyClient.getCurrencies(baseCurrency);
        CurrencyRateDto currencyRateDto = CurrencyRateDto.builder().currencyCode("AZN").currencyRate(1.7F).currencyName("Manat").build();
        currencyRates = List.of(currencyRateDto);
        redisRepository.save(baseCurrency, currencyRates);
        return currencyRates;
    }
}
