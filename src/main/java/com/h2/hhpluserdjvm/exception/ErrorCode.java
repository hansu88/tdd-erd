package com.h2.hhpluserdjvm.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 상품 관련
    GOODS_NOT_FOUND("P001", "상품을 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    INSUFFICIENT_STOCK("P002", "재고가 부족합니다", HttpStatus.BAD_REQUEST),

    // 장바구니 관련
    CART_NOT_FOUND("CART001", "장바구니 항목을 찾을 수 없습니다", HttpStatus.NOT_FOUND),

    // 주문 관련
    ORDER_NOT_FOUND("O002", "주문을 찾을 수 없습니다", HttpStatus.NOT_FOUND),

    // 결제 관련
    INSUFFICIENT_BALANCE("PAY001", "잔액이 부족합니다", HttpStatus.PAYMENT_REQUIRED),

    // 쿠폰 관련
    COUPON_SOLD_OUT("C001", "쿠폰이 모두 소진되었습니다", HttpStatus.CONFLICT),
    COUPON_NOT_FOUND("C002", "쿠폰을 찾을 수 없습니다", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus status;
}