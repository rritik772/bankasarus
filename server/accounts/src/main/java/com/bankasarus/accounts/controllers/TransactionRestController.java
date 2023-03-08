package com.bankasarus.accounts.controllers;

import com.bankasarus.accounts.models.Transaction;
import com.bankasarus.accounts.services.TransactionDataAccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.MessageFormat;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/account")
public class TransactionRestController {
    @Autowired
    TransactionDataAccessService service;

    @GetMapping("{email}/transactions")
    public ResponseEntity<List<Transaction>> getAllTransaction(@PathVariable String email) {
        log.info(MessageFormat.format("getAllTransaction called with email {0}", email));

        return ResponseEntity.ok(service.getTransactionByEmail(email));
    }

    @GetMapping("{email}/mini-statement")
    public ResponseEntity<List<Transaction>> getMiniStatement(@PathVariable String email) {
        log.info(MessageFormat.format("getMiniStatement called with email {0}", email));

        return ResponseEntity.ok(service.getLastSixTransactionsByEmail(email));
    }

    @GetMapping("{email}/month-transactions")
    public ResponseEntity<List<Transaction>> getThisMonthTransaction(@PathVariable String email) {
        log.info(MessageFormat.format("getThisMonthTransactions called with email {0}", email));

        return ResponseEntity.ok(service.getTransactionsForThisMonth(email));
    }

    @GetMapping("{email}/after-date/{date}")
    public ResponseEntity<List<Transaction>> getTransactionAfter(@PathVariable String email, @PathVariable String date) {
        log.info(MessageFormat.format("getTransactionsAfter called with email {0} and date {1}", email, date));
        return ResponseEntity.ok(service.getTransactionsAfter(email, Date.valueOf(date)));
    }

}
