package com.unibank.unitech.controller;

import com.unibank.unitech.security.JwtAuthenticationFilter;
import com.unibank.unitech.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.unibank.unitech.common.JsonFiles.REGISTER_USER;
import static com.unibank.unitech.common.JsonFiles.SIGN_IN;
import static com.unibank.unitech.common.JsonFiles.TOKEN;
import static com.unibank.unitech.common.TestConstants.AUTHENTICATION_TOKEN;
import static com.unibank.unitech.common.TestConstants.SIGN_IN_DTO;
import static com.unibank.unitech.common.TestConstants.USER_REGISTER_DTO;
import static com.unibank.unitech.common.TestUtil.json;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = JwtAuthenticationFilter.class)})
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AuthService authService;

    @Test
    void registerUser_Should_ReturnSuccess() throws Exception {
        mockMvc.perform(post("/v1/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(REGISTER_USER)))
                .andExpect(status().isOk());
        then(authService).should().signUp(USER_REGISTER_DTO);
    }

    @Test
    void signIn_Should_ReturnSuccess() throws Exception {
        given(authService.signIn(SIGN_IN_DTO)).willReturn(AUTHENTICATION_TOKEN);
        final String expectedResult = json(TOKEN);

        mockMvc.perform(post("/v1/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(SIGN_IN)))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
        then(authService).should().signIn(SIGN_IN_DTO);
    }
}
