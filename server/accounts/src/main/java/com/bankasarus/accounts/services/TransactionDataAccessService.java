package com.bankasarus.accounts.services;

import com.bankasarus.accounts.models.Transaction;
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

    public List<Transaction> getTransactionByAccountId(String email) {
        return repository.getTransactionByEmail(email);
    }

    public List<Transaction> getLastSixTransactionsById(String email) {
        return repository.getLastSixTransactions(email);
    }

    public List<Transaction> getTransactionsForThisMonth(String email) {
        YearMonth yearMonth = YearMonth.now();
        Date firstOfMonth = Date.valueOf(yearMonth.atDay(1));

        return repository.getTransactionAfterDate(email, firstOfMonth);
    }

    public List<Transaction> getTransactionsAfter(String email, Date date) {
        return repository.getTransactionAfterDate(email, date);
    }

}
