package com.wallet.resource.transactionevent;

import com.wallet.domain.entity.Pagination;
import com.wallet.domain.entity.PaginationFilter;
import com.wallet.domain.entity.TransactionEvents;
import com.wallet.domain.gateway.TransactionEventsGateway;
import com.wallet.resource.repository.TransactionEventRepository;
import com.wallet.resource.repository.dao.TransactionEventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.stream.Collectors;

@Component
public class TransactionEventsGatewayImpl implements TransactionEventsGateway {
    private final TransactionEventRepository transactionEventRepository;

    public TransactionEventsGatewayImpl(TransactionEventRepository transactionEventRepository) {
        this.transactionEventRepository = transactionEventRepository;
    }

    @Override
    public void create(TransactionEvents transactionEvents) {
        transactionEventRepository.save(new TransactionEventEntity(transactionEvents));
    }

    @Override
    public Pagination<TransactionEvents> getTransactionEvents(String walletId, PaginationFilter paginationFilter) {
        Pageable pageable = PageRequest.of(paginationFilter.getPage(), paginationFilter.getSize());
        Page<TransactionEventEntity> result = transactionEventRepository.findByWalletIdAndCreatedAtBetween(
                walletId,
                paginationFilter.getStartDate().atStartOfDay(),
                paginationFilter.getEndDate().atTime(LocalTime.MAX),
                pageable
        );

        return  new Pagination<TransactionEvents>(
                result.getContent().stream().map(TransactionEventEntity::toDomain).collect(Collectors.toList()),
                result.getNumber(),
                result.getNumberOfElements(),
                result.getTotalPages()
        );
    }
}
