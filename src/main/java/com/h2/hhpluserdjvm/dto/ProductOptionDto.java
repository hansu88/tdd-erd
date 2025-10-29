package com.h2.hhpluserdjvm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionDto {
    private Long optionId;
    private Long productId;
    private String optionName; // 빨강-L 같은 형식
    private Integer additionalPrice;
    private String status; // ACTIVE, INACTIVE
    private Integer availableStock; // 재고 수량 추가
}