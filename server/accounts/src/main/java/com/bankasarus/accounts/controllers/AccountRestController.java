package com.bankasarus.accounts.controllers;

import com.bankasarus.accounts.model.Account;
import com.bankasarus.accounts.model.Transaction;
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

    @GetMapping("{id}/transactions")
    public ResponseEntity<List<Transaction>> getAllTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(transactionDataAccessService.getTransactionByAccountId(id));
    }

    @GetMapping("{id}/mini-statement")
    public ResponseEntity<List<Transaction>> getMiniStatement(@PathVariable Long id) {
        return ResponseEntity.ok(transactionDataAccessService.getLastSixTransactionsById(id));
    }

    @GetMapping("{id}/month-transactions")
    public ResponseEntity<List<Transaction>> getThisMonthTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(transactionDataAccessService.getTransactionsForThisMonth(id));
    }

    @PostMapping
    public ResponseEntity<Transaction> insertTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionDataAccessService.insertTransaction(transaction));
    }

}
