package com.wallet.domain.service;

import com.wallet.domain.entity.PaginationFilter;
import com.wallet.domain.entity.Transaction;
import com.wallet.domain.entity.Transfer;
import com.wallet.domain.entity.Wallet;
import com.wallet.domain.exception.InvalidAmountException;
import com.wallet.domain.exception.NotFoundException;
import com.wallet.domain.exception.WalletAlreadyExistsException;
import com.wallet.domain.gateway.TransactionEventsGateway;
import com.wallet.domain.gateway.WalletGateway;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import static com.wallet.domain.enums.TransactionType.WITHDRAW;

@Service
public class WalletService {

    private final WalletGateway walletGateway;
    private final TransactionEventsGateway transactionEventsGateway;

    public WalletService(WalletGateway walletGateway, TransactionEventsGateway transactionEventsGateway) {
    	this.walletGateway = walletGateway;
        this.transactionEventsGateway = transactionEventsGateway;
    }

    public Wallet create(Wallet wallet) throws WalletAlreadyExistsException {
        try {
            System.out.println("creating wallet for user=" + wallet.getUserId());
            return walletGateway.create(wallet);
        } catch (DataIntegrityViolationException e) {
            throw new WalletAlreadyExistsException("wallet already exists for user=" + wallet.getUserId());
        }
    }

    @Transactional
    public boolean makeTransaction(Transaction transaction) throws NotFoundException, InvalidAmountException {
        String userId = getWalletById(transaction.getWalletId()).getUserId();

        boolean result;
        if (transaction.getTransactionType() == WITHDRAW) {
            result = withdraw(transaction);
        } else {
            result = deposit(transaction);
        }
        addEvent(transaction, userId);
        return result;
    }

    @Transactional
    public boolean makeTransfer(Transfer  transfer) throws NotFoundException, InvalidAmountException {
        Wallet originWallet = getWalletById(transfer.getFromAccountId());
        Wallet targetWallet = getWalletById(transfer.getToAccountId());

        Transaction transactionMade = transfer.toTransferMadeTransaction();
        Transaction transactionReceived = transfer.toTransferReceivedTransaction();

        withdraw(transactionMade);
        addEvent(transactionMade, originWallet.getUserId());

        deposit(transactionReceived);
        addEvent(transactionReceived, targetWallet.getUserId());

        return true;
    }

    public Wallet getWalletById(String id) throws NotFoundException {
        Wallet wallet = walletGateway.getById(id);
        if (wallet == null) {
            throw new NotFoundException("Not found wallet for id="+id);
        }
        return wallet;
    }

    private boolean withdraw(Transaction transaction) throws InvalidAmountException {
        try {
            return walletGateway.withdraw(transaction.getWalletId(), transaction.getAmount());
        } catch (DataIntegrityViolationException e) {
            throw new InvalidAmountException("invalid amount for this operation");
        }
    }

    private boolean deposit(Transaction transaction) {
        return walletGateway.deposit(transaction.getWalletId(), transaction.getAmount());
    }

    private void addEvent(Transaction transaction, String userId) {
        transactionEventsGateway.create(transaction.toTransactionEvent(userId));
    }
}
