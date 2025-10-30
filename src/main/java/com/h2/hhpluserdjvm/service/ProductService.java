package com.h2.hhpluserdjvm.service;

import com.h2.hhpluserdjvm.dto.ProductDto;
import com.h2.hhpluserdjvm.dto.ProductOptionDto;
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

    /*
    Todo: 하나의 상품 재고 확인
     */

    public List<ProductOptionDto> getProductStock(Long productId) {
        ProductOptionDto[] options = restTemplate.getForObject(
                MOCK_API_BASE + "/productOptions?productId=" + productId,
                ProductOptionDto[].class
        );
        return Arrays.asList(options);
    }

    public int getTotalStock(Long productId) {
        List<ProductOptionDto> options = getProductStock(productId);
        return options.stream()
                .mapToInt(ProductOptionDto::getAvailableStock)
                .sum();
    }
}