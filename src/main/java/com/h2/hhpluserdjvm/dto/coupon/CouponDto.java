package com.h2.hhpluserdjvm.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {
    private Long couponId;
    private String couponName;
    private String discountType; // RATE, AMOUNT
    private Integer discountValue;
    private Integer totalQuantity;
    private Integer issuedQuantity;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
}