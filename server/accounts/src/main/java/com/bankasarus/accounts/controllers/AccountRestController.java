package com.bankasarus.accounts.controllers;

import com.bankasarus.accounts.models.Account;
import com.bankasarus.accounts.models.Transaction;
import com.bankasarus.accounts.services.AccountDataAccessService;
import com.bankasarus.accounts.services.TransactionDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountRestController {

    @Autowired
    AccountDataAccessService accountDataAccessService;

    @Autowired
    TransactionDataAccessService transactionDataAccessService;

    @GetMapping("{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Optional<Account> account = accountDataAccessService.findAccountById(id);
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @GetMapping("{email}/by-email")
    public ResponseEntity<Account> getAccountByEmail(@PathVariable String email){
        Optional<Account> account = accountDataAccessService.findAccountByEmail(email);
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @GetMapping("{email}/transactions")
    public ResponseEntity<List<Transaction>> getAllTransaction(@PathVariable String email) {
        return ResponseEntity.ok(transactionDataAccessService.getTransactionByAccountId(email));
    }

    @GetMapping("{email}/mini-statement")
    public ResponseEntity<List<Transaction>> getMiniStatement(@PathVariable String email) {
        return ResponseEntity.ok(transactionDataAccessService.getLastSixTransactionsById(email));
    }

    @GetMapping("{email}/month-transactions")
    public ResponseEntity<List<Transaction>> getThisMonthTransaction(@PathVariable String email) {
        return ResponseEntity.ok(transactionDataAccessService.getTransactionsForThisMonth(email));
    }

    @PostMapping
    public ResponseEntity<Transaction> insertTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionDataAccessService.insertTransaction(transaction));
    }

}
