package com.h2.hhpluserdjvm.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {
    private Long cartId;
    private Long memberId;
    private Long optionId;
    private Integer quantity;
    private LocalDateTime createdAt;

    // 상품/옵션 정보는 필요하면 추후 추가
    private Long productId;
    private String productName;
    private String optionName;
}