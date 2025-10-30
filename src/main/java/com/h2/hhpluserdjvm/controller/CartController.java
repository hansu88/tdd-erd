package com.h2.hhpluserdjvm.controller;

import com.h2.hhpluserdjvm.dto.cart.CartResponseDto;
import com.h2.hhpluserdjvm.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")  // 수정: 따옴표 빠진 부분 수정
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartResponseDto> getCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{memberId}")
    public List<CartResponseDto> getCart(@PathVariable Long memberId) {
        return cartService.getCart(memberId);
    }

}
