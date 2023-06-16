package com.vitos.orderservice.controller;

import com.vitos.orderservice.dto.OrderRequest;
import com.vitos.orderservice.dto.OrderResponse;
import com.vitos.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @CircuitBreaker(name = "payment", fallbackMethod = "fallbackMethod")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest){

        OrderResponse order = orderService.placeOrder(orderRequest);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    public ResponseEntity<?> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
        return new ResponseEntity<>("Please try later", HttpStatus.REQUEST_TIMEOUT);
    }

}
