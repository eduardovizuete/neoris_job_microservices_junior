package com.neorisjob.juniorneorismicro.service.impl;

import com.neorisjob.juniorneorismicro.entity.Account;
import com.neorisjob.juniorneorismicro.entity.Transaction;
import com.neorisjob.juniorneorismicro.repository.AccountRepository;
import com.neorisjob.juniorneorismicro.repository.TransactionRepository;
import com.neorisjob.juniorneorismicro.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        Optional<Account> account = accountRepository.findById(transaction.getAccount().getId());
        transaction.setAccount(account.get());
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        return transaction.get();
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        Transaction existingTx = transactionRepository.findById(transaction.getId()).get();
        existingTx.setDate(transaction.getDate());
        existingTx.setType(transaction.getType());
        existingTx.setAmount(transaction.getAmount());
        existingTx.setBalance(transaction.getBalance());

        return transactionRepository.save(existingTx);
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

}
