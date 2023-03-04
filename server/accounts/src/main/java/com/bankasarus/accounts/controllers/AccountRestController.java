package com.bankasarus.accounts.controllers;

import com.bankasarus.accounts.model.Account;
import com.bankasarus.accounts.services.AccountDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountRestController {

    @Autowired
    AccountDataAccessService service;

    @GetMapping("{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Optional<Account> account = service.findAccountById(id);
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @GetMapping("{id}/mini-statement")
    public ResponseEntity<Object> getMiniStatement(@PathVariable Long id) {
        return ResponseEntity.ok("Not Implemented!!!");
    }

    @GetMapping("{id}/transactions")
    public ResponseEntity<Object> getAllTransaction(@PathVariable Long id) {
        // TODO
        // get All transaction
        return ResponseEntity.ok("Not Implemented!!!");
    }

    @GetMapping("{id}/month-transactions")
    public ResponseEntity<Object> getThisMonthTransaction(@PathVariable Long id) {
        return ResponseEntity.ok("Not Implemented");
    }

}
