package com.wallet.resource.repository;

import com.wallet.resource.repository.dao.WalletEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface WalletRepository extends CrudRepository <WalletEntity, String> {

    @Modifying()
    @Query("UPDATE WalletEntity SET amount = amount - :amount, updatedAt = CURRENT_TIMESTAMP where id = :id")
    public int withdraw(
            @Param("id") String id,
            @Param("amount") BigDecimal amount
    );

    @Modifying()
    @Query("UPDATE WalletEntity SET amount = amount + :amount, updatedAt = CURRENT_TIMESTAMP where id = :id")
    public int deposit(
            @Param("id") String id,
            @Param("amount") BigDecimal amount
    );
}
