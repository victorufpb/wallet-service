package com.wallet.application.web.controller.dto.request;

import com.wallet.domain.entity.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.wallet.application.util.IdGenerator.generateId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletRequest {
    private String userId;
    private BigDecimal amount;

    public Wallet toDomain() {
        BigDecimal newAmount = amount != null ? amount : BigDecimal.ZERO;
        return new Wallet(generateId(), userId, newAmount, "ACTIVE", LocalDateTime.now());
    }
}
