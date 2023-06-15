package com.vitos.productservice.service;

import com.vitos.productservice.dto.ProductRequest;
import com.vitos.productservice.dto.ProductResponse;
import com.vitos.productservice.model.Product;
import com.vitos.productservice.repository.ProductRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Builder
@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse add(ProductRequest productRequest) {
        log.info("Adding new product [{}]", productRequest);

        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .category(productRequest.getCategory())
                .build();

        Product newProduct = productRepository.save(product);

        ProductResponse productResponse = ProductResponse.builder()
                .id(newProduct.getId())
                .name(newProduct.getName())
                .category(newProduct.getCategory())
                .price(newProduct.getPrice())
                .build();

        log.info("Added new product [{}]", productResponse);

        return productResponse;
    }

    public List<ProductResponse> list() {
        log.info("Getting product list");

        List<ProductResponse> list = productRepository.findAll()
                .stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .price(product.getPrice())
                        .category(product.getCategory())
                        .name(product.getName()).build())
                .collect(Collectors.toList());

        log.info("Product list [{}]", list.size());

        return list;
    }

    public ProductResponse findById(String id) throws Exception {
        log.info("Finding product by id [{}]", id);

        Product product = productRepository.findById(id).orElseThrow(
                () -> new Exception(String.format("Product[%s] not found", id)));

        log.info("Found product with id [{}]", id);

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .build();

    }

    public ProductResponse findByName(String name) throws Exception {
        log.info("Finding product by name [{}]", name);

        Product product = productRepository.findByName(name).orElseThrow(
                () -> new Exception(String.format("Product[%s] not found", name)));

        log.info("Found product with name [{}]", name);

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .build();
    }
}
