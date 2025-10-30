package com.h2.hhpluserdjvm.controller;

import com.h2.hhpluserdjvm.dto.request.OrderCreateRequest;
import com.h2.hhpluserdjvm.dto.request.PaymentRequest;
import com.h2.hhpluserdjvm.dto.response.OrderResponse;
import com.h2.hhpluserdjvm.dto.response.PaymentResponse;
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
        // Mock 데이터 반환
        List<OrderResponse.OrderItemDto> mockOrderItems = List.of(
            OrderResponse.OrderItemDto.builder()
                .goodsTitle("나이키 에어포스 1")
                .variantName("화이트/260mm")
                .qty(2)
                .price(120000)
                .build(),
            OrderResponse.OrderItemDto.builder()
                .goodsTitle("아디다스 슈퍼스타")
                .variantName("블랙/270mm")
                .qty(1)
                .price(110000)
                .build()
        );

        return ResponseEntity.ok(OrderResponse.builder()
            .oid(1L)
            .items(mockOrderItems)
            .totalAmount(350000)
            .discount(10000)
            .finalAmount(340000)
            .status("PENDING")
            .build());
    }

    @PostMapping("/{oid}/pay")
    @Operation(summary = "주문 결제", description = "생성된 주문에 대해 결제를 진행합니다 (oid=888: 주문없음, uid=999: 잔액부족)")
    @ApiResponse(responseCode = "200", description = "결제 성공")
    @ApiResponse(responseCode = "404", description = "주문을 찾을 수 없습니다 (O002)")
    @ApiResponse(responseCode = "402", description = "잔액이 부족합니다 (PAY001)")
    public ResponseEntity<PaymentResponse> processPayment(
        @Parameter(description = "주문 ID (888 입력 시 주문없음 에러)", example = "1")
        @PathVariable Long oid,
        @Valid @RequestBody PaymentRequest request
    ) {
        // Mock: oid가 888이면 주문 없음 에러
        if (oid == 888) {
            throw new BusinessException(ErrorCode.ORDER_NOT_FOUND);
        }

        // Mock: uid가 999이면 잔액 부족 에러
        if (request.getUid() == 999) {
            throw new BusinessException(ErrorCode.INSUFFICIENT_BALANCE);
        }

        // TODO: Service 구현 후 연결
        return ResponseEntity.ok(PaymentResponse.builder()
            .pid(1L).oid(oid).amt(240000).status("SUCCESS").remainingBalance(760000).build());
    }
}