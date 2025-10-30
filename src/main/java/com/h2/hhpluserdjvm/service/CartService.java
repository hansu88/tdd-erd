package com.h2.hhpluserdjvm.service;

import com.h2.hhpluserdjvm.dto.CartDto;
import com.h2.hhpluserdjvm.dto.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
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
    public List<CartDto> getAllCarts() {
        CartDto[] carts = restTemplate.getForObject(MOCK_API_BASE + "/carts/", CartDto[].class);
        return Arrays.asList(carts);
    }

    /*
     * Todo: 회원별 장바구니 조회  , Map → DTO 변환
     */
    public List<CartDto> getCart(Long memberId) {
        Map[] carts = restTemplate.getForObject(MOCK_API_BASE + "/carts?memberId=" + memberId, Map[].class);
        if (carts == null) {
            return Collections.emptyList();
        }

        List<CartDto> result = new ArrayList<>();
        for (Map cart : carts) {
            CartDto dto = mapToCartDto(cart);
            result.add(dto);
        }

        return result;
    }

    private CartDto mapToCartDto(Map cart) {
        CartDto dto = new CartDto();
        dto.setCartId(((Number) cart.get("cartId")).longValue());
        dto.setMemberId(((Number) cart.get("memberId")).longValue());
        dto.setOptionId(((Number) cart.get("optionId")).longValue());
        dto.setQuantity(((Number) cart.get("quantity")).intValue());
        dto.setCreatedAt(LocalDateTime.parse(cart.get("createdAt").toString()));
        return dto;
    }


}