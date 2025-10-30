package com.h2.hhpluserdjvm.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long orderId;
    private String status; // PENDING, CONFIRMED, CANCELLED
    private Integer subtotalAmount; // 합계 금액
    private Integer discountAmount; // 할인 금액
    private Integer totalAmount; // 최종 결제 금액
    private LocalDateTime createdAt;
    private List<OrderItemResponseDto> items; // 주문 상품 목록
}