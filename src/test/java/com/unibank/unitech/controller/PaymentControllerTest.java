package com.unibank.unitech.controller;

import com.unibank.unitech.security.JwtAuthenticationFilter;
import com.unibank.unitech.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.unibank.unitech.common.JsonFiles.MAKE_PAYMENT;
import static com.unibank.unitech.common.TestConstants.PAYMENT_DTO;
import static com.unibank.unitech.common.TestUtil.json;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PaymentController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = JwtAuthenticationFilter.class)})
@AutoConfigureMockMvc(addFilters = false)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PaymentService paymentService;

    @Test
    void makePayment_Should_ReturnSuccess() throws Exception {
        mockMvc.perform(post("/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(MAKE_PAYMENT)))
                .andExpect(status().isOk());
        then(paymentService).should().makePayment(PAYMENT_DTO);
    }
}
