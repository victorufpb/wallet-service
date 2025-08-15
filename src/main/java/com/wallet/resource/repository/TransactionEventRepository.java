package com.wallet.resource.repository;

import com.wallet.resource.repository.dao.TransactionEventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface TransactionEventRepository extends JpaRepository<TransactionEventEntity, String> {
    public Page<TransactionEventEntity> findByWalletIdAndCreatedAtBetween(
            String walletId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            Pageable pageable
    );
}
