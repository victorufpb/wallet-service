package com.wallet.domain.entity;

import com.wallet.domain.enums.Action;
import com.wallet.domain.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.wallet.application.util.IdGenerator.generateId;

@Data
@AllArgsConstructor
public class Transaction {
    private String walletId;
    private TransactionType transactionType;
    private BigDecimal amount;
    private Action action;

    public TransactionEvents toTransactionEvent(String userId) {
        return new TransactionEvents(
                generateId(),
                walletId,
                userId,
                transactionType.name(),
                action,
                amount,
                LocalDateTime.now()
        );
    }
}
