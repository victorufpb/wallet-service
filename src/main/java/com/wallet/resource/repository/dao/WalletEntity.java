package com.wallet.resource.repository.dao;

import com.wallet.domain.entity.User;
import com.wallet.domain.entity.Wallet;
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
@Table(name = "wallet")
public class WalletEntity {
	@Id
	private String id;
	private String userId;
	private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public WalletEntity(Wallet wallet) {
		id = wallet.getId();
		userId = wallet.getUserId();
		amount = wallet.getAmount();
        status = wallet.getStatus();
		createdAt = wallet.getCreatedAt();
		updatedAt = LocalDateTime.now();
	}
	
	public Wallet toDomain() {
		return new Wallet(id, userId, amount, status, createdAt);
	}
}
