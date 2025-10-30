package com.h2.hhpluserdjvm.controller;

import com.h2.hhpluserdjvm.dto.request.CouponIssueRequest;
import com.h2.hhpluserdjvm.dto.response.CouponIssueResponse;
import com.h2.hhpluserdjvm.dto.response.UserCouponDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupon")
@Tag(name = "쿠폰", description = "쿠폰 관련 API")
public class CouponController {

    @PostMapping("/{cid}/issue")
    @Operation(summary = "쿠폰 발급", description = "선착순 쿠폰을 발급받습니다")
    @ApiResponse(responseCode = "200", description = "발급 성공")
    @ApiResponse(responseCode = "409", description = "쿠폰이 모두 소진되었습니다 (C001)")
    public ResponseEntity<CouponIssueResponse> issueCoupon(
        @Parameter(description = "쿠폰 ID", example = "1")
        @PathVariable Long cid,
        @Valid @RequestBody CouponIssueRequest request
    ) {
        // TODO: Service 구현 후 연결
        return ResponseEntity.ok(CouponIssueResponse.builder()
            .ucid(1L).couponName("").discountAmount(0).state("ACTIVE").remainingQuantity(0).build());
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
        return ResponseEntity.ok(List.of());
    }
}