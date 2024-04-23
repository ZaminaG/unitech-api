package com.unibank.unitech.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibank.unitech.exception.model.CommonErrorResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class AuthenticationFailureHandler implements AuthenticationEntryPoint {

    private static final String CONTENT_TYPE = "application/json";
    private static final String ERROR_MESSAGE = "Incorrect username or password";
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error("Authentication failed for request: {}", request.getRequestURI(), authException);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(CONTENT_TYPE);
        var errorResponse = new CommonErrorResponse("forbidden", ERROR_MESSAGE);
        writeResponse(response, errorResponse);
    }

    private void writeResponse(HttpServletResponse response, CommonErrorResponse errorResponse) throws IOException {
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
