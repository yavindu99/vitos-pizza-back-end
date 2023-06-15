package com.vitos.orderservice.service;

import com.vitos.orderservice.dto.OrderRequest;
import com.vitos.orderservice.dto.OrderRequestItem;
import com.vitos.orderservice.dto.OrderResponse;
import com.vitos.orderservice.dto.OrderResponseItem;
import com.vitos.orderservice.model.Order;
import com.vitos.orderservice.model.OrderItem;
import com.vitos.orderservice.repsitory.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponse placeOrder(OrderRequest orderRequest){

        Order order = mapOrderRequestToOrder(orderRequest);
        Order newOrder = orderRepository.save(order);

        return mapOrderToOrderResponse(newOrder);

    }

    private OrderResponse mapOrderToOrderResponse(Order order) {

        return OrderResponse.builder()
                .id(order.getId())
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
