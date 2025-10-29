package com.h2.hhpluserdjvm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long memberId;
    private String email;
    private String name;
    private Integer balance;
    private String status; // ACTIVE, INACTIVE
    private LocalDateTime createdAt;
}