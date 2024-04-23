package com.unibank.unitech.dao.repository;

import com.unibank.unitech.client.model.CurrencyRateDto;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CurrencyRedisRepository {
    private static final String KEY_PREFIX = "unitech.currency.rates";
    private final RedisTemplate<String, List<CurrencyRateDto>> redisTemplate;

    public void save(String identifier, List<CurrencyRateDto> currencies) {
        var key = processKey(identifier);
        redisTemplate.opsForValue().set(key, currencies);
        redisTemplate.expire(key, 1, TimeUnit.MINUTES);
    }

    public List<CurrencyRateDto> read(String identifier) {
        return redisTemplate.opsForValue().get(processKey(identifier));
    }

    private String processKey(String identifier) {
        return KEY_PREFIX.concat(identifier);
    }
}
