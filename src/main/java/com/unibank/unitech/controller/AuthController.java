package com.unibank.unitech.controller;

import com.unibank.unitech.model.dto.AuthenticationToken;
import com.unibank.unitech.model.dto.SignInDto;
import com.unibank.unitech.model.dto.UserRegisterDto;
import com.unibank.unitech.service.AuthService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationToken> signIn(@RequestBody @Valid SignInDto signInDto) {
        return ResponseEntity.ok(authService.signIn(signInDto));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        authService.signUp(userRegisterDto);
        return ResponseEntity.ok().build();
    }
}
