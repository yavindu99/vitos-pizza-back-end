package com.vitos.productservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductResponse {

    private String id;

    private String name;

    private String category;

    private BigDecimal price;
}
