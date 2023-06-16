package com.vitos.productservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "product")
public class Product {

    @Id
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
