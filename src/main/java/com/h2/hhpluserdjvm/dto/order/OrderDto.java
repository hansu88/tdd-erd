package com.h2.hhpluserdjvm.dto.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private Long memberId;
    private Long memberCouponId; // nullable
    private String status; // PENDING, CONFIRMED, CANCELLED
    private Integer finalAmount;
    private LocalDateTime createdAt;
}