package com.h2.hhpluserdjvm.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@Schema(description = "사용자 보유 쿠폰 정보")
public class UserCouponDto {

    @Schema(description = "사용자 쿠폰 ID", example = "1")
    private Long ucid;

    @Schema(description = "쿠폰 ID", example = "1")
    private Long cid;

    @Schema(description = "쿠폰명", example = "신규 가입 10% 할인")
    private String couponName;

    @Schema(description = "할인 금액", example = "10000")
    private Integer discountAmount;

    @Schema(description = "쿠폰 상태", example = "ACTIVE")
    private String state;

    @Schema(description = "발급 일시", example = "2024-01-15T10:30:00")
    private LocalDateTime issuedAt;

    @Schema(description = "만료 일시", example = "2024-02-15T23:59:59")
    private LocalDateTime expiredAt;
}