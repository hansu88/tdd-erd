package com.h2.hhpluserdjvm.controller;

import com.h2.hhpluserdjvm.dto.request.OrderCreateRequest;
import com.h2.hhpluserdjvm.dto.request.PaymentRequest;
import com.h2.hhpluserdjvm.dto.response.OrderResponse;
import com.h2.hhpluserdjvm.dto.response.PaymentResponse;
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
@RequestMapping("/api/order")
@Tag(name = "주문/결제", description = "주문 및 결제 관련 API")
public class OrdController {

    @PostMapping
    @Operation(summary = "주문 생성", description = "상품을 주문합니다. 재고를 확인하고 차감합니다.")
    @ApiResponse(responseCode = "200", description = "주문 생성 성공")
    @ApiResponse(responseCode = "400", description = "재고가 부족합니다 (P002)")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        // TODO: Service 구현 후 연결
        return ResponseEntity.ok(OrderResponse.builder()
            .oid(1L).items(List.of()).totalAmount(0).discount(0).finalAmount(0).status("PENDING").build());
    }

    @PostMapping("/{oid}/pay")
    @Operation(summary = "주문 결제", description = "생성된 주문에 대해 결제를 진행합니다")
    @ApiResponse(responseCode = "200", description = "결제 성공")
    @ApiResponse(responseCode = "404", description = "주문을 찾을 수 없습니다 (O002)")
    @ApiResponse(responseCode = "402", description = "잔액이 부족합니다 (PAY001)")
    public ResponseEntity<PaymentResponse> processPayment(
        @Parameter(description = "주문 ID", example = "1")
        @PathVariable Long oid,
        @Valid @RequestBody PaymentRequest request
    ) {
        // TODO: Service 구현 후 연결
        return ResponseEntity.ok(PaymentResponse.builder()
            .pid(1L).oid(oid).amt(0).status("SUCCESS").remainingBalance(0).build());
    }
}