package com.wallet.application.web.controller;

import com.wallet.application.web.controller.dto.request.TransactionRequest;
import com.wallet.application.web.controller.dto.request.TransferRequest;
import com.wallet.application.web.controller.dto.request.WalletRequest;
import com.wallet.application.web.controller.dto.response.SuccessfulResponse;
import com.wallet.domain.entity.Pagination;
import com.wallet.domain.entity.PaginationFilter;
import com.wallet.domain.entity.TransactionEvents;
import com.wallet.domain.entity.Wallet;
import com.wallet.domain.exception.InvalidAmountException;
import com.wallet.domain.exception.NotFoundException;
import com.wallet.domain.exception.WalletAlreadyExistsException;
import com.wallet.domain.gateway.TransactionEventsGateway;
import com.wallet.domain.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequestMapping(value = "wallets")
@RestController
public class WalletController {
    Logger logger = LoggerFactory.getLogger(WalletController.class);
    private final WalletService walletService;
    private final TransactionEventsGateway transactionEventsGateway;

    public WalletController(WalletService walletService,  TransactionEventsGateway transactionEventsGateway) {
        this.walletService = walletService;
        this.transactionEventsGateway = transactionEventsGateway;
    }

    @PostMapping
    public ResponseEntity<Wallet> createUser(@RequestBody WalletRequest walletRequest) throws WalletAlreadyExistsException {
        MDC.put("user_id", walletRequest.getUserId());
        logger.info("Creating wallet for user_id={}", walletRequest.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(walletService.create(walletRequest.toDomain()));
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Wallet> getWallet(@PathVariable String id) throws NotFoundException {
        MDC.put("wallet_id", id);
        logger.info("Fetching wallet for id={}", id);
		return ResponseEntity.ok(walletService.getWalletById(id));

    }

    @PostMapping(value = "/{id}/balance/transaction")
    public ResponseEntity<SuccessfulResponse> makeTransaction(
            @PathVariable String id,
            @RequestBody TransactionRequest transactionRequest
    ) throws NotFoundException, InvalidAmountException {
        MDC.put("wallet_id", id);
        logger.info("Making transaction={} for id={}", transactionRequest.getTransactionType(), id);
        boolean updated = walletService.makeTransaction(transactionRequest.toDomain(id));
        return ResponseEntity.ok(new SuccessfulResponse(updated));
    }

    @PostMapping(value = "/{id}/balance/transfer")
    public ResponseEntity<SuccessfulResponse> makeTransfer(
            @PathVariable String id,
            @RequestBody TransferRequest  transferRequest
    ) throws InvalidAmountException, NotFoundException {
        MDC.put("wallet_id", id);
        logger.info("Making transfer from wallet_id={} to wallet_id={}", id, transferRequest.getTargetAccountId());
        boolean transferred = walletService.makeTransfer(transferRequest.toDomain(id));
        return ResponseEntity.ok(new SuccessfulResponse(transferred));
    }

    @GetMapping(value = "/{id}/events")
    public ResponseEntity<Pagination<TransactionEvents>> getWalletBalance(
            @PathVariable String id,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(name = "start_date", required = false) LocalDate startDate,
            @RequestParam(name = "end_date", required = false) LocalDate endDate
    ) throws NotFoundException {
        MDC.put("wallet_id", id);
        logger.info("Fetching wallet balance events for wallet_id={}", id);
        return ResponseEntity.ok(transactionEventsGateway.getTransactionEvents(id, new PaginationFilter(page, size, startDate, endDate)));
    }
}
