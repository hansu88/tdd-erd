package com.h2.hhpluserdjvm.controller;

import com.h2.hhpluserdjvm.dto.order.OrderResponseDto;
import com.h2.hhpluserdjvm.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponseDto createOrder(@RequestParam Long memberId,
                                        @RequestParam(required = false) Long memberCouponId) {
        return orderService.createOrder(memberId, memberCouponId);
    }
}
