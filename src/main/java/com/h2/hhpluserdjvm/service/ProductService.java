package com.h2.hhpluserdjvm.service;

import com.h2.hhpluserdjvm.dto.order.OrderDto;
import com.h2.hhpluserdjvm.dto.order.OrderItemDto;
import com.h2.hhpluserdjvm.dto.product.PopularProductResponseDto;
import com.h2.hhpluserdjvm.dto.product.ProductDto;
import com.h2.hhpluserdjvm.dto.product.ProductOptionDto;
import com.h2.hhpluserdjvm.dto.product.ProductResponseDto;
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

    /**
     * TODO: 전체 상품 조회 (ProductResponseDto)
     */
    public List<ProductResponseDto> getAllProducts() {
        ProductDto[] products = restTemplate.getForObject(MOCK_API_BASE + "/products", ProductDto[].class);
        if (products == null) return Collections.emptyList();

        List<ProductResponseDto> result = new ArrayList<>();
        for (ProductDto p : products) {
            int totalStock = getTotalStock(p.getProductId());
            result.add(new ProductResponseDto(
                    p.getProductId(),
                    p.getProductName(),
                    p.getBasePrice(),
                    p.getStatus(),
                    totalStock
            ));
        }
        return result;
    }

    /**
     * TODO: 상품 재고 합계 조회
     */
    public int getTotalStock(Long productId) {
        ProductOptionDto[] options = restTemplate.getForObject(
                MOCK_API_BASE + "/productOptions?productId=" + productId,
                ProductOptionDto[].class
        );
        if (options == null) return 0;

        return Arrays.stream(options)
                .mapToInt(ProductOptionDto::getAvailableStock)
                .sum();
    }

    /**
     * TODO: 인기 상품 조회 (PopularProductResponseDto)
     */
    public List<PopularProductResponseDto> getPopularProducts(int days, int limit) {
        ProductDto[] allProducts = restTemplate.getForObject(MOCK_API_BASE + "/products", ProductDto[].class);
        OrderItemDto[] orderItems = restTemplate.getForObject(MOCK_API_BASE + "/orderItems", OrderItemDto[].class);
        OrderDto[] orders = restTemplate.getForObject(MOCK_API_BASE + "/orders", OrderDto[].class);

        if (allProducts == null || orderItems == null || orders == null) return Collections.emptyList();

        LocalDateTime cutoff = LocalDateTime.now().minusDays(days);

        // 결제 완료 주문 필터링
        Set<Long> paidOrderIds = Arrays.stream(orders)
                .filter(o -> "PAID".equals(o.getStatus()))
                .filter(o -> o.getCreatedAt().isAfter(cutoff))
                .map(OrderDto::getOrderId)
                .collect(Collectors.toSet());

        // 상품별 판매량 계산
        Map<Long, Integer> salesMap = Arrays.stream(orderItems)
                .filter(oi -> paidOrderIds.contains(oi.getOrderId()))
                .collect(Collectors.groupingBy(OrderItemDto::getProductId,
                        Collectors.summingInt(OrderItemDto::getQuantity)));

        // DTO 변환 (판매량은 안보여줌)
        return Arrays.stream(allProducts)
                .filter(p -> salesMap.containsKey(p.getProductId()))
                .sorted(Comparator.comparingInt((ProductDto p) -> salesMap.get(p.getProductId())).reversed())
                .limit(limit)
                .map(p -> new PopularProductResponseDto(
                        p.getProductId(),
                        p.getProductName(),
                        p.getBasePrice(),
                        p.getStatus()
                ))
                .collect(Collectors.toList());
    }
}