package com.h2.hhpluserdjvm.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Schema(description = "장바구니 항목")
public class CartItemDto {

    @Schema(description = "장바구니 ID", example = "1")
    private Long cid;

    @Schema(description = "상품 ID", example = "1")
    private Long gid;

    @Schema(description = "상품명", example = "나이키 에어포스")
    private String goodsTitle;

    @Schema(description = "옵션 ID", example = "1")
    private Long vid;

    @Schema(description = "옵션명", example = "블랙 280mm")
    private String variantName;

    @Schema(description = "수량", example = "2")
    private Integer cnt;

    @Schema(description = "단가", example = "125000")
    private Integer price;

    @Schema(description = "합계 금액", example = "250000")
    private Integer totalPrice;
}