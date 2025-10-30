package com.h2.hhpluserdjvm.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Schema(description = "주문 생성 응답")
public class OrderResponse {

    @Schema(description = "주문 ID", example = "1")
    private Long oid;

    @Schema(description = "주문 상품 목록")
    private List<OrderItemDto> items;

    @Schema(description = "총 금액", example = "250000")
    private Integer totalAmount;

    @Schema(description = "할인 금액", example = "10000")
    private Integer discount;

    @Schema(description = "최종 결제 금액", example = "240000")
    private Integer finalAmount;

    @Schema(description = "주문 상태", example = "PENDING")
    private String status;

    @Getter
    @AllArgsConstructor
    @Builder
    @Schema(description = "주문 상품 항목")
    public static class OrderItemDto {
        @Schema(description = "상품명", example = "나이키 에어포스")
        private String goodsTitle;

        @Schema(description = "옵션명", example = "블랙 280mm")
        private String variantName;

        @Schema(description = "수량", example = "2")
        private Integer qty;

        @Schema(description = "단가", example = "125000")
        private Integer price;
    }
}