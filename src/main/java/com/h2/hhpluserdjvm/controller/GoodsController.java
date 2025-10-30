package com.h2.hhpluserdjvm.controller;

import com.h2.hhpluserdjvm.dto.response.GoodsDetailDto;
import com.h2.hhpluserdjvm.dto.response.GoodsDto;
import com.h2.hhpluserdjvm.dto.response.PopularGoodsDto;
import com.h2.hhpluserdjvm.exception.BusinessException;
import com.h2.hhpluserdjvm.exception.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
        // Mock 데이터 반환
        List<GoodsDto> mockGoods = List.of(
            GoodsDto.builder()
                .gid(1L)
                .title("나이키 에어포스 1")
                .description("클래식 운동화")
                .price(120000)
                .build(),
            GoodsDto.builder()
                .gid(2L)
                .title("아디다스 슈퍼스타")
                .description("레트로 스타일 운동화")
                .price(110000)
                .build(),
            GoodsDto.builder()
                .gid(3L)
                .title("뉴발란스 530")
                .description("편안한 러닝화")
                .price(130000)
                .build()
        );
        return ResponseEntity.ok(mockGoods);
    }

    @GetMapping("/{gid}")
    @Operation(summary = "상품 상세 조회", description = "특정 상품의 상세 정보와 옵션을 조회합니다")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @ApiResponse(responseCode = "404", description = "상품을 찾을 수 없습니다 (P001)")
    public ResponseEntity<GoodsDetailDto> getGoodsDetail(
        @Parameter(description = "상품 ID (999 입력 시 404 에러)", example = "1")
        @PathVariable Long gid
    ) {
        // Mock: gid가 999이면 에러 발생 (에러 처리 테스트용)
        if (gid == 999) {
            throw new BusinessException(ErrorCode.GOODS_NOT_FOUND);
        }

        // TODO: Service 구현 후 연결
        return ResponseEntity.ok(GoodsDetailDto.builder()
            .gid(gid).title("나이키 에어포스").description("클래식 운동화").price(120000).variants(List.of()).build());
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
        // Mock 데이터 반환
        List<PopularGoodsDto> mockPopularGoods = List.of(
            PopularGoodsDto.builder()
                .gid(1L)
                .title("나이키 에어포스 1")
                .salesCount(150L)
                .price(120000)
                .build(),
            PopularGoodsDto.builder()
                .gid(3L)
                .title("뉴발란스 530")
                .salesCount(120L)
                .price(130000)
                .build(),
            PopularGoodsDto.builder()
                .gid(2L)
                .title("아디다스 슈퍼스타")
                .salesCount(95L)
                .price(110000)
                .build()
        );
        return ResponseEntity.ok(mockPopularGoods);
    }
}