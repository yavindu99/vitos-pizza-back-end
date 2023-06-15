package com.vitos.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private String orderNo;
    private List<OrderResponseItem> orderItems;

    private String customerName;

    private String customerContactNo;

    private String customerAddress;
}
