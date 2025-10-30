package com.h2.hhpluserdjvm.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "장바구니 추가 요청")
public class CartAddRequest {

    @NotNull
    @Schema(description = "사용자 ID", example = "1")
    private Long uid;

    @NotNull
    @Schema(description = "상품 옵션 ID", example = "1")
    private Long vid;

    @NotNull
    @Positive
    @Schema(description = "수량", example = "2")
    private Integer cnt;
}