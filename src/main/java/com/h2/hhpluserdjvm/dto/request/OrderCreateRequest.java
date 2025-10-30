package com.h2.hhpluserdjvm.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "주문 생성 요청")
public class OrderCreateRequest {

    @NotNull
    @Schema(description = "사용자 ID", example = "1")
    private Long uid;

    @NotEmpty
    @Schema(description = "주문 상품 목록")
    private List<OrderItem> items;

    @Schema(description = "쿠폰 ID (선택)", example = "1")
    private Long couponId;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "주문 상품 항목")
    public static class OrderItem {
        @NotNull
        @Schema(description = "상품 옵션 ID", example = "1")
        private Long vid;

        @NotNull
        @Positive
        @Schema(description = "수량", example = "2")
        private Integer qty;
    }
}