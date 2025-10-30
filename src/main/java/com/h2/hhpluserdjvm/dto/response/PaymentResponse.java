package com.h2.hhpluserdjvm.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Schema(description = "결제 응답")
public class PaymentResponse {

    @Schema(description = "결제 ID", example = "1")
    private Long pid;

    @Schema(description = "주문 ID", example = "1")
    private Long oid;

    @Schema(description = "결제 금액", example = "240000")
    private Integer amt;

    @Schema(description = "결제 상태", example = "SUCCESS")
    private String status;

    @Schema(description = "잔여 잔액", example = "760000")
    private Integer remainingBalance;
}