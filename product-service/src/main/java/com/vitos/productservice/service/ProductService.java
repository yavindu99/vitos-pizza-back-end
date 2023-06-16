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
                .tags(productRequest.getTags())
                .favorite(productRequest.getFavorite())
                .stars(productRequest.getStars())
                .imageUrl(productRequest.getImageUrl())
                .origins(productRequest.getOrigins())
                .cookTime(productRequest.getCookTime())
                .category(productRequest.getCategory())
                .build();

        Product newProduct = productRepository.save(product);

        ProductResponse productResponse = mapToProductResponse(newProduct);

        log.info("Added new product [{}]", productResponse);

        return productResponse;
    }

    public List<ProductResponse> list() {
        log.info("Getting product list");

        List<ProductResponse> list = productRepository.findAll()
                .stream()
                .map(product -> mapToProductResponse(product))
                .collect(Collectors.toList());

        log.info("Product list [{}]", list.size());

        return list;
    }

    public ProductResponse findById(String id) throws Exception {
        log.info("Finding product by id [{}]", id);

        Product product = productRepository.findById(id).orElseThrow(
                () -> new Exception(String.format("Product[%s] not found", id)));

        log.info("Found product with id [{}]", id);

        return mapToProductResponse(product);

    }

    public ProductResponse findByName(String name) throws Exception {
        log.info("Finding product by name [{}]", name);

        Product product = productRepository.findByName(name).orElseThrow(
                () -> new Exception(String.format("Product[%s] not found", name)));

        log.info("Found product with name [{}]", name);

        return mapToProductResponse(product);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .tags(product.getTags())
                .favorite(product.getFavorite())
                .stars(product.getStars())
                .imageUrl(product.getImageUrl())
                .origins(product.getOrigins())
                .cookTime(product.getCookTime())
                .category(product.getCategory())
                .build();
    }
}
