package com.vitos.paymentservice.controller;

import com.vitos.paymentservice.dto.PaymentRequest;
import com.vitos.paymentservice.dto.PaymentResponse;
import com.vitos.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    public ResponseEntity<?> pay(@RequestBody PaymentRequest paymentRequest){

        PaymentResponse payment = paymentService.pay(paymentRequest);

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
    
}
