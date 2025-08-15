package com.wallet.application.web.controller.dto.request;

import com.wallet.domain.entity.Transaction;
import com.wallet.domain.enums.Action;
import com.wallet.domain.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.wallet.domain.enums.Action.MADE;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequest {
    private TransactionType transactionType;
    private BigDecimal amount;

    public Transaction toDomain(String walletId) {
        return new Transaction(walletId, transactionType, amount, MADE);
    }
}
