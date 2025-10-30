package com.h2.hhpluserdjvm.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponResponseDto {
    private Long couponId;
    private String couponName;
    private String discountType;
    private Integer discountValue;
    private Integer totalQuantity;
}

