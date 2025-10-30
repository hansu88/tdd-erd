package com.h2.hhpluserdjvm.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long productId;
    private String productName;
    private Integer basePrice;
    private String status;
    private Integer totalStock; // 재고 합계
}