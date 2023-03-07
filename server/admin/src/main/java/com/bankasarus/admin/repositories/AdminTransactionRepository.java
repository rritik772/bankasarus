package com.bankasarus.admin.repositories;

import com.bankasarus.accounts.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface AdminTransactionRepository extends JpaRepository<Transaction, Long> {
    String transactionsAfterDate = "SELECT * from transaction where email = :email AND date >= :date";
    String lastSixTransactions = "SELECT * from transaction where email = :email ORDER BY date DESC LIMIT 6";
    String largestNTransaction = "SELECT * from transaction where email = :email ORDER BY transaction_amt DESC LIMIT :n";

    List<Transaction> getTransactionByEmail(String email);

    @Query(value = transactionsAfterDate, nativeQuery = true)
    List<Transaction> getTransactionAfterDate(String email, Date date);

    @Query(value = lastSixTransactions, nativeQuery = true)
    List<Transaction> getLastSixTransactions(String email);

    @Query(value = largestNTransaction, nativeQuery = true)
    List<Transaction> getLargestNTransactions(String email, int n);
}