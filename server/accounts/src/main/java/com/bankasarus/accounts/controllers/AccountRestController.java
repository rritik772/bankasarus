package com.bankasarus.accounts.controllers;

import com.bankasarus.accounts.models.Account;
import com.bankasarus.accounts.services.AccountDataAccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.Optional;

@Slf4j
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

    @GetMapping("{email}/by-email")
    public ResponseEntity<Account> getAccountByEmail(@PathVariable String email){
        log.info(MessageFormat.format("getAccountByEmail called with email {0}", email));

        Optional<Account> account = service.findAccountByEmail(email);
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

}
