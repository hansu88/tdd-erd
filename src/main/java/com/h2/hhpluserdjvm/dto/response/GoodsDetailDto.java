package com.h2.hhpluserdjvm.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Schema(description = "상품 상세 정보")
public class GoodsDetailDto {

    @Schema(description = "상품 ID", example = "1")
    private Long gid;

    @Schema(description = "상품명", example = "나이키 에어포스")
    private String title;

    @Schema(description = "상품 설명", example = "인기 운동화")
    private String description;

    @Schema(description = "기본 가격", example = "120000")
    private Integer price;

    @Schema(description = "상품 옵션 목록")
    private List<GoodsVariantDto> variants;
}