package com.unibank.unitech.config;

import com.unibank.unitech.client.model.CurrencyRateDto;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, List<CurrencyRateDto>> currencyRateRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, List<CurrencyRateDto>> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
}
