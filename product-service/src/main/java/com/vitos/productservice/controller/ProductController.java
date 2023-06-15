package com.vitos.productservice.controller;

import com.vitos.productservice.dto.ProductRequest;
import com.vitos.productservice.dto.ProductResponse;
import com.vitos.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> add(@RequestBody ProductRequest productRequest){

        ProductResponse product = productService.add(productRequest);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/list")
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
    @GetMapping("by-name/{name}")
    public ResponseEntity<?> getByName(@PathVariable(name = "name") String name){

        ProductResponse product;
        try {
            product = productService.findByName(name);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
