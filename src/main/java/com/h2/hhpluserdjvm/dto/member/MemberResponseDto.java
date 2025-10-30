package com.h2.hhpluserdjvm.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private Long memberId;
    private String email;
    private String name;
    private String status;
}