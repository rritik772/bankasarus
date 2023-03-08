package com.bankasarus.admin.controllers;

import com.bankasarus.accounts.models.Transaction;
import com.bankasarus.admin.services.TransactionsDataAccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.MessageFormat;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/transactions")
public class TransactionRestController {

    TransactionsDataAccessService service;

    @GetMapping("{email}")
    public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable String email) {
        log.info(MessageFormat.format("getAllTransactions called with email: {0}", email));

        return ResponseEntity.ok(service.getAllTransactions(email));
    }

    @GetMapping("transaction-after/{email}/{date}")
    public ResponseEntity<List<Transaction>> getTransactionAfter(@PathVariable String email, @PathVariable String date) {
        log.info(MessageFormat.format("getTransactionAfter called with email: {0} and date {1}", email, date));

        return ResponseEntity.ok(service.getTransactionAfter(email, Date.valueOf(date)));
    }

    @GetMapping("six-transactions/{email}")
    public ResponseEntity<List<Transaction>> getLastSixTransactions(@PathVariable String email) {
        log.info(MessageFormat.format("getLastSixTransactions called with email: {0}", email));

        return ResponseEntity.ok(service.getLastSixTransactions(email));
    }
    @GetMapping("last-n-transactions/{email}/{n}")
    public ResponseEntity<List<Transaction>> getLastNTransactions(@PathVariable String email, @PathVariable int n) {
        log.info(MessageFormat.format("getLastNTransactions called with email: {0} and n {0}", email, n));

        return ResponseEntity.ok(service.getLastNTransactions(email, n));
    }
}
