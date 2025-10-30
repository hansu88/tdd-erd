package com.h2.hhpluserdjvm.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "결제 요청")
public class PaymentRequest {

    @NotNull
    @Schema(description = "사용자 ID", example = "1")
    private Long uid;

    @NotNull
    @Schema(description = "결제 방법", example = "BALANCE", allowableValues = {"BALANCE", "CARD"})
    private String method;
}