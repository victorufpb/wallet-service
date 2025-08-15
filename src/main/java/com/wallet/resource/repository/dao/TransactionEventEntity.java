package com.wallet.resource.repository.dao;

import com.wallet.domain.entity.TransactionEvents;
import com.wallet.domain.enums.Action;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction_events")
public class TransactionEventEntity {
	@Id
    private String id;
    private String walletId;
    private String userId;
    private String operation;
    private String action;
    private BigDecimal amount;
    private LocalDateTime createdAt;

	public TransactionEventEntity(TransactionEvents transactionEvents) {
		id = transactionEvents.getId();
        walletId = transactionEvents.getWalletId();
		userId = transactionEvents.getUserId();
        operation = transactionEvents.getOperation();
        action = transactionEvents.getAction().name();
		amount = transactionEvents.getAmount();
		createdAt = transactionEvents.getCreatedAt();
	}
	
	public TransactionEvents toDomain() {
		return new TransactionEvents(id, walletId, userId, operation, Action.valueOf(action), amount, createdAt);
	}
}
