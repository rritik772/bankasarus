package com.bankasarus.accounts.services;

import com.bankasarus.accounts.model.Transaction;
import com.bankasarus.accounts.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.YearMonth;
import java.util.List;

@Service
public class TransactionDataAccessService {
    @Autowired
    TransactionRepository repository;

    public List<Transaction> getTransactionByAccountId(Long id) {
        return repository.getTransactionByAccountId(id);
    }

    public List<Transaction> getLastSixTransactionsById(Long id) {
        return repository.getLastSixTransactions(id);
    }

    public List<Transaction> getTransactionsForThisMonth(Long id) {
        YearMonth yearMonth = YearMonth.now();
        Date firstOfMonth = Date.valueOf(yearMonth.atDay(1));

        return repository.getTransactionAfterDate(id, firstOfMonth);
    }

    public List<Transaction> getTransactionsAfter(Long id, Date date) {
        return repository.getTransactionAfterDate(id, date);
    }

    public Transaction insertTransaction(Transaction transaction) {
        return repository.saveAndFlush(transaction);

    }

}
