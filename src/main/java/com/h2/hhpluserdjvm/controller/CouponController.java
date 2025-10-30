package com.h2.hhpluserdjvm.controller;

import com.h2.hhpluserdjvm.dto.request.CouponIssueRequest;
import com.h2.hhpluserdjvm.dto.response.CouponIssueResponse;
import com.h2.hhpluserdjvm.dto.response.UserCouponDto;
import com.h2.hhpluserdjvm.exception.BusinessException;
import com.h2.hhpluserdjvm.exception.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/coupon")
@Tag(name = "쿠폰", description = "쿠폰 관련 API")
public class CouponController {

    @PostMapping("/{cid}/issue")
    @Operation(summary = "쿠폰 발급", description = "선착순 쿠폰을 발급받습니다 (cid=999일 때 품절 에러)")
    @ApiResponse(responseCode = "200", description = "발급 성공")
    @ApiResponse(responseCode = "409", description = "쿠폰이 모두 소진되었습니다 (C001)")
    public ResponseEntity<CouponIssueResponse> issueCoupon(
        @Parameter(description = "쿠폰 ID (999 입력 시 품절 에러)", example = "1")
        @PathVariable Long cid,
        @Valid @RequestBody CouponIssueRequest request
    ) {
        // Mock: cid가 999이면 쿠폰 품절 에러 발생
        if (cid == 999) {
            throw new BusinessException(ErrorCode.COUPON_SOLD_OUT);
        }

        // TODO: Service 구현 후 연결
        return ResponseEntity.ok(CouponIssueResponse.builder()
            .ucid(1L).couponName("10,000원 할인 쿠폰").discountAmount(10000)
            .state("ACTIVE").remainingQuantity(50).build());
    }

    @GetMapping("/my")
    @Operation(summary = "보유 쿠폰 조회", description = "사용자가 보유한 쿠폰 목록을 조회합니다")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    public ResponseEntity<List<UserCouponDto>> getMyCoupons(
        @Parameter(description = "사용자 ID", example = "1")
        @RequestParam Long uid,
        @Parameter(description = "쿠폰 상태 (선택)", example = "ACTIVE")
        @RequestParam(required = false) String state
    ) {
        // TODO: Service 구현 후 연결
        // Mock 데이터 반환
        LocalDateTime now = LocalDateTime.now();
        List<UserCouponDto> mockCoupons = List.of(
            UserCouponDto.builder()
                .ucid(1L)
                .cid(1L)
                .couponName("10,000원 할인 쿠폰")
                .discountAmount(10000)
                .state("ACTIVE")
                .issuedAt(now.minusDays(5))
                .expiredAt(now.plusDays(25))
                .build(),
            UserCouponDto.builder()
                .ucid(2L)
                .cid(2L)
                .couponName("5,000원 할인 쿠폰")
                .discountAmount(5000)
                .state("ACTIVE")
                .issuedAt(now.minusDays(3))
                .expiredAt(now.plusDays(27))
                .build(),
            UserCouponDto.builder()
                .ucid(3L)
                .cid(3L)
                .couponName("20,000원 할인 쿠폰 (사용완료)")
                .discountAmount(20000)
                .state("USED")
                .issuedAt(now.minusDays(10))
                .expiredAt(now.plusDays(20))
                .build()
        );

        // state 파라미터가 있으면 필터링
        if (state != null && !state.isEmpty()) {
            mockCoupons = mockCoupons.stream()
                .filter(c -> c.getState().equals(state))
                .toList();
        }

        return ResponseEntity.ok(mockCoupons);
    }
}