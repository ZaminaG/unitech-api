package com.unibank.unitech.controller;

import com.unibank.unitech.security.JwtAuthenticationFilter;
import com.unibank.unitech.service.CurrencyService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import static com.unibank.unitech.common.JsonFiles.GET_CURRENCY_RATES;
import static com.unibank.unitech.common.TestConstants.CURRENCY_RATE_DTO;
import static com.unibank.unitech.common.TestUtil.json;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CurrencyController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = JwtAuthenticationFilter.class)})
@AutoConfigureMockMvc(addFilters = false)
class CurrencyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CurrencyService currencyService;

    @Test
    void getCurrencyRates_Should_ReturnSuccess() throws Exception {
        given(currencyService.getCurrencies("USD")).willReturn(List.of(CURRENCY_RATE_DTO));
        final String expectedResult = json(GET_CURRENCY_RATES);
        mockMvc.perform(get("/v1/currencies/{currency}/rates", "USD"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
        then(currencyService).should().getCurrencies("USD");
    }
}
