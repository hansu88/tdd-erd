package com.h2.hhpluserdjvm.controller;

import com.h2.hhpluserdjvm.dto.request.CartAddRequest;
import com.h2.hhpluserdjvm.dto.response.CartItemDto;
import com.h2.hhpluserdjvm.dto.response.CartListResponse;
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
    @Operation(summary = "장바구니 담기", description = "상품을 장바구니에 추가합니다")
    @ApiResponse(responseCode = "200", description = "추가 성공")
    @ApiResponse(responseCode = "400", description = "재고가 부족합니다 (P002)")
    public ResponseEntity<CartItemDto> addToCart(@Valid @RequestBody CartAddRequest request) {
        // TODO: Service 구현 후 연결
        return ResponseEntity.ok(CartItemDto.builder()
            .cid(1L).gid(1L).goodsTitle("").vid(1L).variantName("").cnt(1).price(0).totalPrice(0).build());
    }

    @GetMapping
    @Operation(summary = "장바구니 조회", description = "사용자의 장바구니 목록을 조회합니다")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    public ResponseEntity<CartListResponse> getCartItems(
        @Parameter(description = "사용자 ID", example = "1")
        @RequestParam Long uid
    ) {
        // TODO: Service 구현 후 연결
        return ResponseEntity.ok(CartListResponse.builder().items(List.of()).totalAmount(0).build());
    }

    @DeleteMapping("/{cid}")
    @Operation(summary = "장바구니 삭제", description = "장바구니에서 특정 항목을 삭제합니다")
    @ApiResponse(responseCode = "204", description = "삭제 성공")
    @ApiResponse(responseCode = "404", description = "장바구니 항목을 찾을 수 없습니다 (CART001)")
    public ResponseEntity<Void> deleteCartItem(
        @Parameter(description = "장바구니 ID", example = "1")
        @PathVariable Long cid
    ) {
        // TODO: Service 구현 후 연결
        return ResponseEntity.noContent().build();
    }
}