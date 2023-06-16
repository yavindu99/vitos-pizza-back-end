package com.vitos.productservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductResponse {

    private String id;

    private String name;

    private BigDecimal price;

    private String category;

    private List<String> tags;

    private Boolean favorite;

    private Integer stars;

    private String imageUrl;

    private List<String> origins;

    private String cookTime;
}
