package com.h2.hhpluserdjvm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long orderItemId;
    private Long orderId;
    private Long productId;
    private Long optionId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;
}