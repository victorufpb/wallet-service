package com.wallet.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Wallet {
    private String id;
    private String userId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;
}
