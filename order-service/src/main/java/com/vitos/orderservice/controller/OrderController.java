package com.vitos.orderservice.controller;

import com.vitos.orderservice.dto.OrderRequest;
import com.vitos.orderservice.dto.OrderResponse;
import com.vitos.orderservice.service.OrderService;
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
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest){

        OrderResponse order = orderService.placeOrder(orderRequest);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }


}
