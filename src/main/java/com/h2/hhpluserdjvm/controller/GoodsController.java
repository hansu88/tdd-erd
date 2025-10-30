package com.h2.hhpluserdjvm.controller;

import com.h2.hhpluserdjvm.dto.response.GoodsDetailDto;
import com.h2.hhpluserdjvm.dto.response.GoodsDto;
import com.h2.hhpluserdjvm.dto.response.PopularGoodsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goods")
@Tag(name = "상품", description = "상품 관련 API")
public class GoodsController {

    @GetMapping
    @Operation(
        summary = "전체 상품 조회",
        description = "등록된 모든 상품 목록을 조회합니다"
    )
    @ApiResponse(responseCode = "200", description = "조회 성공")
    public ResponseEntity<List<GoodsDto>> getAllGoods() {
        // TODO: Service 구현 후 연결
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/{gid}")
    @Operation(summary = "상품 상세 조회", description = "특정 상품의 상세 정보와 옵션을 조회합니다")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @ApiResponse(responseCode = "404", description = "상품을 찾을 수 없습니다 (P001)")
    public ResponseEntity<GoodsDetailDto> getGoodsDetail(
        @Parameter(description = "상품 ID", example = "1")
        @PathVariable Long gid
    ) {
        // TODO: Service 구현 후 연결
        return ResponseEntity.ok(GoodsDetailDto.builder()
            .gid(gid).title("").description("").price(0).variants(List.of()).build());
    }

    @GetMapping("/popular")
    @Operation(summary = "인기 상품 조회", description = "최근 N일간 판매량이 많은 상품을 조회합니다")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    public ResponseEntity<List<PopularGoodsDto>> getPopularGoods(
        @Parameter(description = "기준 일수", example = "3")
        @RequestParam(defaultValue = "3") Integer days,
        @Parameter(description = "조회 개수", example = "5")
        @RequestParam(defaultValue = "5") Integer limit
    ) {
        // TODO: Service 구현 후 연결
        return ResponseEntity.ok(List.of());
    }
}