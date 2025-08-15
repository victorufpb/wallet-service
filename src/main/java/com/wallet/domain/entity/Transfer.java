package com.wallet.domain.entity;

import com.wallet.domain.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

import static com.wallet.domain.enums.Action.MADE;
import static com.wallet.domain.enums.Action.RECEIVED;
import static com.wallet.domain.enums.TransactionType.*;

@Data
@AllArgsConstructor
public class Transfer {
    private String fromAccountId;
    private String toAccountId;
    private BigDecimal amount;

    public Transaction toTransferReceivedTransaction() {
        return new Transaction(toAccountId, TRANSFER, amount, RECEIVED);
    }

    public Transaction toTransferMadeTransaction() {
        return new Transaction(fromAccountId, TRANSFER, amount, MADE);
    }
}
