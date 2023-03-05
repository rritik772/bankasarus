package com.bankasarus.accounts.repositories;

import com.bankasarus.accounts.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    String transactionsAfterDate = "SELECT * from transaction where account_id = :id AND date >= :date";
    String lastSixTransactions = "SELECT * from transaction where account_id = :id ORDER BY date DESC LIMIT 6";

    List<Transaction> getTransactionByAccountId(Long id);

    @Query(value = transactionsAfterDate, nativeQuery = true)
    List<Transaction> getTransactionAfterDate(Long id, Date date);

    @Query(value = lastSixTransactions, nativeQuery = true)
    List<Transaction> getLastSixTransactions(Long id);
}
