package com.h2.hhpluserdjvm.dto.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private Integer basePrice;
    private String status; // ACTIVE, SOLD_OUT
    private LocalDateTime createdAt;
}