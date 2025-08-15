package com.wallet.application.web.controller.dto.request;

import com.wallet.domain.entity.Transfer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {
    private String targetAccountId;
    private BigDecimal amount;

    public Transfer toDomain(String accountId) {
        return new Transfer(accountId, targetAccountId, amount);
    }
}
