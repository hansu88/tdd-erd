package com.h2.hhpluserdjvm.service;

import com.h2.hhpluserdjvm.dto.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;


@Service
public class ProductService {

    private final RestTemplate restTemplate;
    private final String MOCK_API_BASE = "http://localhost:3000";

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /*
    Todo: 전체 상품 조회
    */
    public List<ProductDto> getAllProducts() {
        ProductDto[] products = restTemplate.getForObject(MOCK_API_BASE + "/products", ProductDto[].class);
        return Arrays.asList(products);
    }
}