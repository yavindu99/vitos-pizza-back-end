package com.vitos.orderservice.dto;

import com.vitos.orderservice.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

    private String customerName;

    private String customerContactNo;

    private String customerAddress;
    private List<OrderRequestItem> orderItems;
    private PaymentRequest paymentRequest;
}
