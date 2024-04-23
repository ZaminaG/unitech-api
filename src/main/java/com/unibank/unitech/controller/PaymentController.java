package com.unibank.unitech.controller;

import com.unibank.unitech.model.dto.PaymentDto;
import com.unibank.unitech.service.PaymentService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Void> makePayment(@RequestBody @Valid PaymentDto paymentDto) {
        paymentService.makePayment(paymentDto);
        return ResponseEntity.ok().build();
    }
}
