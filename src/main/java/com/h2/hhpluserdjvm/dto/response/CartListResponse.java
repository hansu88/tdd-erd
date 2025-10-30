package com.h2.hhpluserdjvm.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Schema(description = "장바구니 목록 응답")
public class CartListResponse {

    @Schema(description = "장바구니 항목 목록")
    private List<CartItemDto> items;

    @Schema(description = "총 금액", example = "500000")
    private Integer totalAmount;
}