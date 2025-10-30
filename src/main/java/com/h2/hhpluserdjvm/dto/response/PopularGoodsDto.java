package com.h2.hhpluserdjvm.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Schema(description = "인기 상품 정보")
public class PopularGoodsDto {

    @Schema(description = "상품 ID", example = "1")
    private Long gid;

    @Schema(description = "상품명", example = "나이키 에어포스")
    private String title;

    @Schema(description = "판매량", example = "150")
    private Long salesCount;

    @Schema(description = "기본 가격", example = "120000")
    private Integer price;
}