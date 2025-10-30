package com.h2.hhpluserdjvm.dto.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopularProductResponseDto {
    private Long productId;
    private String productName;
    private Integer price;
    private String status; // ACTIVE, SOLD_OUT
}