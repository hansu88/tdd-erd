package com.h2.hhpluserdjvm.service;

import com.h2.hhpluserdjvm.dto.cart.CartDto;
import com.h2.hhpluserdjvm.dto.cart.CartResponseDto;
import com.h2.hhpluserdjvm.dto.product.ProductDto;
import com.h2.hhpluserdjvm.dto.product.ProductOptionDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CartService {

    private final RestTemplate restTemplate;
    private final String MOCK_API_BASE = "http://localhost:3000";

    public CartService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /*
    Todo: 장바구니 현재 전체 조회
     */
    public List<CartResponseDto> getAllCarts() {
        CartDto[] carts = restTemplate.getForObject(MOCK_API_BASE + "/carts/", CartDto[].class);
        if (carts == null) return Collections.emptyList();

        List<CartResponseDto> result = new ArrayList<>();
        for (CartDto cart : carts) {
            result.add(convertToResponseDto(cart));
        }
        return result;
    }

    /*
     * Todo: 회원별 장바구니 조회
     */
    public List<CartResponseDto> getCart(Long memberId) {
        CartDto[] carts = restTemplate.getForObject(MOCK_API_BASE + "/carts?memberId=" + memberId, CartDto[].class);
        if (carts == null) return Collections.emptyList();

        List<CartResponseDto> result = new ArrayList<>();
        for (CartDto cart : carts) {
            result.add(convertToResponseDto(cart));
        }
        return result;
    }

    /*
     * Todo: 필요한 정보  MockApi 활용해서 조회
     */
    private CartResponseDto convertToResponseDto(CartDto cart) {
        // 1. 옵션 단일 조회
        ProductOptionDto[] options = restTemplate.getForObject(
                MOCK_API_BASE + "/productOptions?id=" + cart.getOptionId(),
                ProductOptionDto[].class
        );
        ProductOptionDto option = (options != null && options.length > 0) ? options[0] : null;

        Long productId = option != null ? option.getProductId() : null;
        String optionName = option != null ? option.getOptionName() : null;

        // 2. 상품 단일 조회
        ProductDto[] products = restTemplate.getForObject(
                MOCK_API_BASE + "/products?id=" + productId,
                ProductDto[].class
        );
        ProductDto product = (products != null && products.length > 0) ? products[0] : null;

        String productName = product != null ? product.getProductName() : null;

        // 3. CartResponseDto 생성
        return new CartResponseDto(
                cart.getCartId(),
                cart.getMemberId(),
                cart.getOptionId(),
                cart.getQuantity(),
                cart.getCreatedAt(),
                productId,
                productName,
                optionName
        );
    }
}