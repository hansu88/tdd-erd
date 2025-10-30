package com.h2.hhpluserdjvm.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberCouponResponseDto {
    private Long memberCouponId;
    private Long memberId;
    private Long couponId;
    private String status;
    private LocalDateTime issuedAt;
}