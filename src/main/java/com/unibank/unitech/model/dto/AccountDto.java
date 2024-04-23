package com.unibank.unitech.model.dto;

import com.unibank.unitech.model.enums.AccountStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {
    private String iban;
    private BigDecimal balance;
    private LocalDateTime openingDate;
    private AccountStatus status;
    private String currency;
}
