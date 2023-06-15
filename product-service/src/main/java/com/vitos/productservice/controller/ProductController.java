package com.vitos.productservice.controller;

import com.vitos.productservice.dto.ProductRequest;
import com.vitos.productservice.dto.ProductResponse;
import com.vitos.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductResponse> add(@RequestBody ProductRequest productRequest){

        ProductResponse product = productService.add(productRequest);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> list(){

        List<ProductResponse> products = productService.list();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") String id){

        ProductResponse product;
        try {
            product = productService.findById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
