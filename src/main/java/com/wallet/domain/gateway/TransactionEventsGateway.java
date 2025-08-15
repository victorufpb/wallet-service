package com.wallet.domain.gateway;

import com.wallet.domain.entity.Pagination;
import com.wallet.domain.entity.PaginationFilter;
import com.wallet.domain.entity.TransactionEvents;

public interface TransactionEventsGateway {
    public void create(TransactionEvents transactionEvents);
    public Pagination<TransactionEvents> getTransactionEvents(String walletId, PaginationFilter paginationFilter);
}
