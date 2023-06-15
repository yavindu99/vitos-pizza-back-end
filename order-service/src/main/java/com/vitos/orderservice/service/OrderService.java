package com.vitos.orderservice.service;

import com.vitos.orderservice.dto.*;
import com.vitos.orderservice.model.Order;
import com.vitos.orderservice.model.OrderItem;
import com.vitos.orderservice.repsitory.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public OrderResponse placeOrder(OrderRequest orderRequest){

        Order order = mapOrderRequestToOrder(orderRequest);
        order.setOrderNo(UUID.randomUUID().toString());
        Order newOrder = orderRepository.save(order);

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(newOrder.getOrderNo())
                .payment(orderRequest.getPaymentRequest().getPayment())
                .paymentMethod(orderRequest.getPaymentRequest().getPaymentMethod())
                .payment(orderRequest.getPaymentRequest().getPayment())
                .build();

        webClientBuilder.build().post().uri("http://payment-service/api/payment")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(paymentRequest)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .block();

        return mapOrderToOrderResponse(newOrder);

    }

    private OrderResponse mapOrderToOrderResponse(Order order) {

        return OrderResponse.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .customerAddress(order.getCustomerAddress())
                .customerContactNo(order.getCustomerContactNo())
                .orderItems(mapOrderItemsToOrderResponseItem(order.getOrderItems()))
                .build();

    }

    private List<OrderResponseItem> mapOrderItemsToOrderResponseItem(List<OrderItem> orderItems) {

        return orderItems.stream().map(orderItem -> OrderResponseItem.builder()
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .skuNumber(orderItem.getSkuNumber())
                .build()).collect(Collectors.toList());
    }

    private Order mapOrderRequestToOrder(OrderRequest orderRequest) {

        return Order.builder()
                .orderItems(mapOrderRequestItemsToOrderItems(orderRequest.getOrderItems()))
                .customerName(orderRequest.getCustomerName())
                .customerAddress(orderRequest.getCustomerAddress())
                .customerContactNo(orderRequest.getCustomerContactNo())
                .build();

    }

    private List<OrderItem> mapOrderRequestItemsToOrderItems(List<OrderRequestItem> orderItems) {

        return orderItems.stream().map(orderRequestItem -> OrderItem.builder()
                .price(orderRequestItem.getPrice())
                .quantity(orderRequestItem.getQuantity())
                .skuNumber(orderRequestItem.getSkuNumber())
                .build()).collect(Collectors.toList());

    }

}
