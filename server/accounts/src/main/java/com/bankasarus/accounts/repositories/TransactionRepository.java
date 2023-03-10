package com.bankasarus.accounts.repositories;

import com.bankasarus.accounts.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    String transactionsAfterDate = "SELECT * from transaction where email = :email AND date >= :date";
    String lastSixTransactions = "SELECT * from transaction where email = :email ORDER BY date DESC LIMIT 6";

    List<Transaction> getTransactionByEmail(String email);

    @Query(value = transactionsAfterDate, nativeQuery = true)
    List<Transaction> getTransactionAfterDate(@Param("email") String email, @Param("date") Date date);

    @Query(value = lastSixTransactions, nativeQuery = true)
    List<Transaction> getLastSixTransactions(@Param("email") String email);

}
