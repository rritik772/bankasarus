package com.bankasarus.customer.controllers;


import com.bankasarus.accounts.models.Account;
import com.bankasarus.accounts.models.Transaction;
import com.bankasarus.customer.models.Customer;
import com.bankasarus.customer.services.CustomerDataAccessService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerInfoRestController {

    @Autowired
    CustomerDataAccessService service;

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable("email") String email) {
        log.info(MessageFormat.format("Fetching getCustomerByEmail() with email {0}", email));

        Optional<Customer> customer = service.getCustomerByEmail(email);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @GetMapping("{email}/has-account")
    public boolean isCustomerHavingAccount(@PathVariable("email") String email) {
        return service.isCustomerHavingAccount(email);
    }


    private final String ACCOUNTS_API_BASE_URL = "http://localhost:8085/account/";
    private final String ACCOUNTS_API_CIRCUIT_BREAKER = "ACCOUNTS_API";

    @GetMapping("{email}/account")
    @CircuitBreaker(name = ACCOUNTS_API_CIRCUIT_BREAKER, fallbackMethod = "getAccountInfoFallback")
    public ResponseEntity<Account> getAccountInfo(@PathVariable("email") String email) {
        log.info(MessageFormat.format("Fetching getAccountInfo() with email {0}", email));

        if (!isCustomerHavingAccount(email))
            return ResponseEntity.status(404).body(null);

        String uri = MessageFormat.format(ACCOUNTS_API_BASE_URL + "{0}/by-email", email);
        return ResponseEntity.ok(restTemplate.getForObject(uri, Account.class));
    }

    public ResponseEntity<Account> getAccountInfoFallback(Exception e) {
        log.warn(MessageFormat.format("getAccountInfoFallback called with exception {0}", e.getMessage()));

        return ResponseEntity.status(404).body(null);
    }


    @GetMapping("{email}/transactions")
    @CircuitBreaker(name = ACCOUNTS_API_CIRCUIT_BREAKER, fallbackMethod = "getAllTransactionsFallback")
    public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable("email") String email) {
        log.info(MessageFormat.format("Fetching getAllTransactions with email {0}", email));

        if (!isCustomerHavingAccount(email))
            return ResponseEntity.status(404).body(null);

        String uri = MessageFormat.format(ACCOUNTS_API_BASE_URL + "{0}/transactions", email);
        return ResponseEntity.ok(restTemplate.getForObject(uri, List.class));
    }

    public ResponseEntity<List<Transaction>> getAllTransactionsFallback(Exception e) {
        log.warn(MessageFormat.format("getAllTransactionFallback called with exception {0}", e.getMessage()));

        return ResponseEntity.status(404).body(Arrays.asList());
    }

}
