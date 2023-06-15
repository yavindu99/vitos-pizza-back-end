package com.vitos.paymentservice.service;

import com.vitos.paymentservice.dto.PaymentRequest;
import com.vitos.paymentservice.dto.PaymentResponse;
import com.vitos.paymentservice.model.Payment;
import com.vitos.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    public PaymentResponse pay(PaymentRequest paymentRequest){

        //Call to a payment gateway
        Payment payment  = mapPaymentRquestToPayment(paymentRequest);
        Payment newPayment = paymentRepository.save(payment);

        return mapPaymentToPaymentResponse(newPayment);
    }
    private PaymentResponse mapPaymentToPaymentResponse(Payment newPayment) {

        return PaymentResponse.builder()
                .id(newPayment.getId())
                .orderId(newPayment.getOrderId())
                .paymentMethod(newPayment.getPaymentMethod())
                .payment(newPayment.getPayment())
                .build();

    }
    private Payment mapPaymentRquestToPayment(PaymentRequest paymentRequest) {

        return Payment.builder()
                .paymentMethod(paymentRequest.getPaymentMethod())
                .payment(paymentRequest.getPayment())
                .orderId(paymentRequest.getOrderId())
                .build();

    }
}
