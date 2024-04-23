package com.unibank.unitech.controller;

import com.unibank.unitech.model.dto.AccountDto;
import com.unibank.unitech.service.AccountService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/active")
    public ResponseEntity<List<AccountDto>> getUserActiveAccounts() {
        return ResponseEntity.ok(accountService.getUserActiveAccounts());
    }
}
