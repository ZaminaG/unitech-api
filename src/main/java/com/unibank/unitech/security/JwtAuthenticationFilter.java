package com.unibank.unitech.security;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtTokenManager jwtTokenManager;
    private final String authHeader;
    private final String tokenPrefix;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService,
                                   JwtTokenManager jwtTokenManager,
                                   @Value("${spring.jwt.header.string}") String authHeader,
                                   @Value("${spring.jwt.token.prefix}") String tokenPrefix) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenManager = jwtTokenManager;
        this.authHeader = authHeader;
        this.tokenPrefix = tokenPrefix;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {
        final var authorizationHeader = request.getHeader(authHeader);
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(tokenPrefix)) {
            authenticateRequest(authorizationHeader, request);
        }
        chain.doFilter(request, response);
    }

    private void authenticateRequest(String authorizationHeader, HttpServletRequest request) {
        var authToken = authorizationHeader.replace(tokenPrefix, "");
        var pin = jwtTokenManager.getUsernameFromToken(authToken);
        if (StringUtils.hasText(pin) && isUserNotAuthenticated()) {
            var userDetails = userDetailsService.loadUserByUsername(pin);
            if (jwtTokenManager.validateToken(authToken, userDetails.getUsername())) {
                var authenticationToken = createAuthenticationToken(userDetails, request);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
    }

    private boolean isUserNotAuthenticated() {
        return Objects.isNull(SecurityContextHolder.getContext().getAuthentication());
    }

    private UsernamePasswordAuthenticationToken createAuthenticationToken(UserDetails userDetails,
                                                                          HttpServletRequest request) {
        var authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authenticationToken;
    }
}