package com.unibank.unitech.controller;

import com.unibank.unitech.security.JwtAuthenticationFilter;
import com.unibank.unitech.service.AccountService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import static com.unibank.unitech.common.JsonFiles.GET_ACCOUNTS;
import static com.unibank.unitech.common.TestConstants.ACCOUNT_DTO;
import static com.unibank.unitech.common.TestUtil.json;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = JwtAuthenticationFilter.class)})
@AutoConfigureMockMvc(addFilters = false)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    AccountService accountService;

    @Test
    void getActiveAccounts_Should_ReturnSuccess() throws Exception {
        given(accountService.getUserActiveAccounts()).willReturn(List.of(ACCOUNT_DTO));
        final String expectedResult = json(GET_ACCOUNTS);
        mockMvc.perform(get("/v1/accounts/active"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
        then(accountService).should().getUserActiveAccounts();
    }
}
