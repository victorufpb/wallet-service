package com.wallet.domain.gateway;

import com.wallet.domain.entity.PaginationFilter;
import com.wallet.domain.entity.Wallet;

import java.math.BigDecimal;

public interface WalletGateway {

    public Wallet create(Wallet wallet);
    public Wallet getById(String walletId);
    public boolean withdraw(String walletId, BigDecimal amount);
    public boolean deposit(String walletId, BigDecimal amount);
}
