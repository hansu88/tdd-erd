package com.h2.hhpluserdjvm.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Long cartId;
    private Long memberId;
    private Long optionId;
    private Integer quantity;
    private LocalDateTime createdAt;
}