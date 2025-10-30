package com.h2.hhpluserdjvm.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Schema(description = "쿠폰 발급 응답")
public class CouponIssueResponse {

    @Schema(description = "사용자 쿠폰 ID", example = "1")
    private Long ucid;

    @Schema(description = "쿠폰명", example = "신규 가입 10% 할인")
    private String couponName;

    @Schema(description = "할인 금액", example = "10000")
    private Integer discountAmount;

    @Schema(description = "쿠폰 상태", example = "ACTIVE")
    private String state;

    @Schema(description = "남은 수량", example = "42")
    private Integer remainingQuantity;
}