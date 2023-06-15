package com.vitos.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpStatus;
import com.vitos.productservice.dto.ProductRequest;
import com.vitos.productservice.repository.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;

    @Container
    final static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:latest"));

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getProductRequest())));

        Assert.assertEquals(1, productRepository.findAll().size());
    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("Chicken Pizza")
                .category("Chicken")
                .price(BigDecimal.TEN)
                .build();
    }

}
