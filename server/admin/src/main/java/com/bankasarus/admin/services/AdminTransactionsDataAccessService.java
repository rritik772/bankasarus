package com.bankasarus.admin.services;

import com.bankasarus.accounts.models.Transaction;
import com.bankasarus.admin.repositories.AdminTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AdminTransactionsDataAccessService {
    @Autowired
    AdminTransactionRepository repository;

    public List<Transaction> getAllTransactions(String email) {
        return repository.getTransactionByEmail(email);
    }

    public List<Transaction> getTransactionAfter(String email, Date date) {
        return repository.getTransactionAfterDate(email, date);
    }

    public List<Transaction> getLastSixTransactions(String email) {
        return repository.getLastSixTransactions(email);
    }

    public List<Transaction> getLastNTransactions(String email, int n) {
        return repository.getLargestNTransactions(email, n);
    }
}
