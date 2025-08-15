package com.wallet.domain.entity;

import com.wallet.domain.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class TransactionEvents {
    private String id;
    private String walletId;
    private String userId;
    private String operation;
    private Action action;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
