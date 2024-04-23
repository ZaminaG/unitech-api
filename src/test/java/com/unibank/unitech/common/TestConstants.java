package com.unibank.unitech.common;

import com.unibank.unitech.client.model.CurrencyRateDto;
import com.unibank.unitech.model.dto.AccountDto;
import com.unibank.unitech.model.dto.AuthenticationToken;
import com.unibank.unitech.model.dto.PaymentDto;
import com.unibank.unitech.model.dto.SignInDto;
import com.unibank.unitech.model.dto.UserRegisterDto;
import com.unibank.unitech.model.enums.AccountStatus;
import java.math.BigDecimal;

public interface TestConstants {

    String CURRENCY = "AZN";

    String IBAN = "AA555";

    CurrencyRateDto CURRENCY_RATE_DTO = CurrencyRateDto.builder()
            .currencyName(CURRENCY)
            .currencyRate(1.7F)
            .currencyCode(CURRENCY).build();

    AccountDto ACCOUNT_DTO = AccountDto.builder()
            .iban(IBAN)
            .status(AccountStatus.ACTIVE)
            .balance(BigDecimal.TEN)
            .currency(CURRENCY)
            .build();

    PaymentDto PAYMENT_DTO = PaymentDto.builder()
            .amount(BigDecimal.ONE)
            .destinationAccount("B555")
            .sourceAccount(IBAN)
            .build();

    AuthenticationToken AUTHENTICATION_TOKEN = AuthenticationToken.builder()
            .token("AAAAAAA")
            .build();

    UserRegisterDto USER_REGISTER_DTO = UserRegisterDto.builder()
            .pin("AA100")
            .name("Gulmammadli")
            .password("3453453453453453sfdvcdnslkvnsd")
            .surname("Zamina")
            .email("zaminagm@gmail.com")
            .build();

    SignInDto SIGN_IN_DTO = SignInDto.builder()
            .password("3453453453453453sfdvcdnslkvnsd")
            .pin("AA100")
            .build();
}
