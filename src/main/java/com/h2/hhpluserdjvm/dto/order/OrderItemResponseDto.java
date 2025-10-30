package com.h2.hhpluserdjvm.dto.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDto {
    private Long productId;
    private String productName;
    private String optionName; // 선택한 옵션
    private Integer quantity;
    private Integer unitPrice;
    private Integer subtotal; // quantity * unitPrice
}