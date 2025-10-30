package com.h2.hhpluserdjvm.controller;

import com.h2.hhpluserdjvm.dto.request.CartAddRequest;
import com.h2.hhpluserdjvm.dto.response.CartItemDto;
import com.h2.hhpluserdjvm.dto.response.CartListResponse;
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
@RequestMapping("/api/cart")
@Tag(name = "장바구니", description = "장바구니 관련 API")
public class CartController {

    @PostMapping
    @Operation(summary = "장바구니 담기", description = "상품을 장바구니에 추가합니다 (cnt >= 100이면 재고부족 에러)")
    @ApiResponse(responseCode = "200", description = "추가 성공")
    @ApiResponse(responseCode = "400", description = "재고가 부족합니다 (P002)")
    public ResponseEntity<CartItemDto> addToCart(@Valid @RequestBody CartAddRequest request) {
        // Mock: 수량이 100 이상이면 재고 부족 에러 발생
        if (request.getCnt() >= 100) {
            throw new BusinessException(ErrorCode.INSUFFICIENT_STOCK);
        }

        // TODO: Service 구현 후 연결
        return ResponseEntity.ok(CartItemDto.builder()
            .cid(1L).gid(1L).goodsTitle("나이키 에어포스").vid(request.getVid())
            .variantName("화이트/260mm").cnt(request.getCnt()).price(120000)
            .totalPrice(120000 * request.getCnt()).build());
    }

    @GetMapping
    @Operation(summary = "장바구니 조회", description = "사용자의 장바구니 목록을 조회합니다")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    public ResponseEntity<CartListResponse> getCartItems(
        @Parameter(description = "사용자 ID", example = "1")
        @RequestParam Long uid
    ) {
        // TODO: Service 구현 후 연결
        // Mock 데이터 반환
        List<CartItemDto> mockItems = List.of(
            CartItemDto.builder()
                .cid(1L)
                .gid(1L)
                .goodsTitle("나이키 에어포스 1")
                .vid(1L)
                .variantName("화이트/260mm")
                .cnt(2)
                .price(120000)
                .totalPrice(240000)
                .build(),
            CartItemDto.builder()
                .cid(2L)
                .gid(2L)
                .goodsTitle("아디다스 슈퍼스타")
                .vid(3L)
                .variantName("블랙/270mm")
                .cnt(1)
                .price(110000)
                .totalPrice(110000)
                .build()
        );

        return ResponseEntity.ok(CartListResponse.builder()
            .items(mockItems)
            .totalAmount(350000)
            .build());
    }

    @DeleteMapping("/{cid}")
    @Operation(summary = "장바구니 삭제", description = "장바구니에서 특정 항목을 삭제합니다 (cid=999일 때 404 에러)")
    @ApiResponse(responseCode = "204", description = "삭제 성공")
    @ApiResponse(responseCode = "404", description = "장바구니 항목을 찾을 수 없습니다 (CART001)")
    public ResponseEntity<Void> deleteCartItem(
        @Parameter(description = "장바구니 ID (999 입력 시 404 에러)", example = "1")
        @PathVariable Long cid
    ) {
        // Mock: cid가 999이면 에러 발생
        if (cid == 999) {
            throw new BusinessException(ErrorCode.CART_NOT_FOUND);
        }

        // TODO: Service 구현 후 연결
        return ResponseEntity.noContent().build();
    }
}