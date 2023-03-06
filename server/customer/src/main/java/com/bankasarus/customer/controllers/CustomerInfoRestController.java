package com.bankasarus.customer.controllers;

import com.bankasarus.accounts.models.Account;

import com.bankasarus.accounts.models.Transaction;
import com.bankasarus.customer.models.Customer;
import com.bankasarus.customer.services.CustomerDataAccessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerInfoRestController {

    @Autowired
    CustomerDataAccessService service;

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable("email") String email) {
        Optional<Customer> customer = service.getCustomerByEmail(email);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @GetMapping("{email}/has-account")
    public boolean isCustomerHavingAccount(@PathVariable("email") String email) {
        return service.isCustomerHavingAccount(email);
    }

    @GetMapping("{email}/account")
    public ResponseEntity<Account> getAccountInfo(@PathVariable("email") String email) {
        if (!isCustomerHavingAccount(email))
            return ResponseEntity.status(404).body(null);

        String uri = MessageFormat.format("http://localhost:8085/{0}/by-email", email);

        try{
            return ResponseEntity.ok(restTemplate.getForObject(uri, Account.class));
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(500).body(null);
        }

    }

    @GetMapping("{email}/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable("email") String email) {
        if (!isCustomerHavingAccount(email))
            return ResponseEntity.status(404).body(null);

        String uri = MessageFormat.format("http://localhost:8085/{0}/transactions", email);
        try {
            return ResponseEntity.ok(restTemplate.getForObject(uri, List.class));
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
