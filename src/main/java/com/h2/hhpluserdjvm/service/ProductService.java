package com.h2.hhpluserdjvm.service;

import com.h2.hhpluserdjvm.dto.OrderDto;
import com.h2.hhpluserdjvm.dto.OrderItemDto;
import com.h2.hhpluserdjvm.dto.ProductDto;
import com.h2.hhpluserdjvm.dto.ProductOptionDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


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

    /*
    Todo: 인기상품 조회
     */
    public List<Map<String, Object>> getPopularProducts(int days, int limit) {
        List<ProductDto> allProducts = getAllProducts();
        OrderItemDto[] orderItems = restTemplate.getForObject(MOCK_API_BASE + "/orderItems", OrderItemDto[].class);
        OrderDto[] orders = restTemplate.getForObject(MOCK_API_BASE + "/orders", OrderDto[].class);

        // 시간 지정 (현재 시간 - 몇일전 기록)
        LocalDateTime cutoff = LocalDateTime.now().minusDays(days);

        // 최근 결제 완료 주문 ID 추출
        Set<Long> recentPaidOrderIds = Arrays.stream(orders)
                .filter(o -> "PAID".equals(o.getStatus()))
                .filter(o -> o.getCreatedAt().isAfter(cutoff))
                .map(OrderDto::getOrderId)
                .collect(Collectors.toSet());

        // 상품별 판매량 합산
        Map<Long, Integer> productCountMap = Arrays.stream(orderItems)
                .filter(oi -> recentPaidOrderIds.contains(oi.getOrderId()))
                .collect(Collectors.groupingBy(OrderItemDto::getProductId,
                        Collectors.summingInt(OrderItemDto::getQuantity)));

        // 인기상품 Top N 추출 및 Map 변환
        List<Map<String, Object>> popularProducts = allProducts.stream()
                .filter(p -> productCountMap.containsKey(p.getProductId()))
                .sorted(Comparator.comparingInt((ProductDto p) -> productCountMap.get(p.getProductId()))
                        .reversed())
                .limit(limit)
                .map(p -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", p.getProductId());
                    map.put("name", p.getProductName());
                    map.put("price", p.getBasePrice());
                    map.put("status", p.getStatus());
                    map.put("soldQuantity", productCountMap.get(p.getProductId()));
                    return map;
                })
                .collect(Collectors.toList());

        return popularProducts;
    }
}