package com.h2.hhpluserdjvm.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Schema(description = "상품 옵션 정보")
public class GoodsVariantDto {

    @Schema(description = "옵션 ID", example = "1")
    private Long vid;

    @Schema(description = "옵션명", example = "블랙 280mm")
    private String name;

    @Schema(description = "추가 금액", example = "5000")
    private Integer additionalPrice;

    @Schema(description = "재고 수량", example = "100")
    private Integer stock;
}