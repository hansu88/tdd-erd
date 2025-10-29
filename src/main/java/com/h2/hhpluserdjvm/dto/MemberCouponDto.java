package com.h2.hhpluserdjvm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberCouponDto {
    private Long memberCouponId;
    private Long memberId;
    private Long couponId;
    private String status; // UNUSED, USED, EXPIRED
    private LocalDateTime issuedAt;
    private LocalDateTime usedAt;
}