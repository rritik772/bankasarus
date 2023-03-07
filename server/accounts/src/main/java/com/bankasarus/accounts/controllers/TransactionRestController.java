package com.bankasarus.accounts.controllers;

import com.bankasarus.accounts.models.Transaction;
import com.bankasarus.accounts.services.TransactionDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionRestController {
    @Autowired
    TransactionDataAccessService service;

    @GetMapping("{email}/transactions")
    public ResponseEntity<List<Transaction>> getAllTransaction(@PathVariable String email) {
        return ResponseEntity.ok(service.getTransactionByEmail(email));
    }

    @GetMapping("{email}/mini-statement")
    public ResponseEntity<List<Transaction>> getMiniStatement(@PathVariable String email) {
        return ResponseEntity.ok(service.getLastSixTransactionsByEmail(email));
    }

    @GetMapping("{email}/month-transactions")
    public ResponseEntity<List<Transaction>> getThisMonthTransaction(@PathVariable String email) {
        return ResponseEntity.ok(service.getTransactionsForThisMonth(email));
    }

}
