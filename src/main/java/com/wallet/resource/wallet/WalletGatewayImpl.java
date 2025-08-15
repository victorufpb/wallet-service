package com.wallet.resource.wallet;

import com.wallet.domain.entity.Wallet;
import com.wallet.domain.gateway.WalletGateway;
import com.wallet.resource.repository.WalletRepository;
import com.wallet.resource.repository.dao.WalletEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WalletGatewayImpl implements WalletGateway {

    private final int SUCCESSFULLY_UPDATED = 1;

    private final WalletRepository walletRepository;

    public WalletGatewayImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet create(Wallet wallet) {
        return walletRepository.save(new WalletEntity(wallet)).toDomain();
    }

    @Override
    public Wallet getById(String walletId) {
        WalletEntity wallet = walletRepository.findById(walletId).orElse(null);
        return wallet != null ? wallet.toDomain() : null;
    }

    @Override
    public boolean withdraw(String walletId, BigDecimal amount) {
        return walletRepository.withdraw(walletId, amount) == SUCCESSFULLY_UPDATED;
    }

    @Override
    public boolean deposit(String walletId, BigDecimal amount) {
        return walletRepository.deposit(walletId, amount) == SUCCESSFULLY_UPDATED;
    }

}
