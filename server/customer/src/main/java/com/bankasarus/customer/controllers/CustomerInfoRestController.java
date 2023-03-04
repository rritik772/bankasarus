package com.bankasarus.customer.controllers;

import com.bankasarus.customer.exceptions.UserNotFoundException;
import com.bankasarus.customer.models.Customer;
import com.bankasarus.customer.services.CustomerDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    public void getAccountInfo(@PathVariable("email") String email) {
        if (!isCustomerHavingAccount(email)) return;

        // TODO
        // get accounts information from accounts api
    }

    @GetMapping("{email}/transactions")
    public void getAllTransactions(@PathVariable("email") String email) {
        if (!isCustomerHavingAccount(email)) return;

        // TODO
        // get all transactions from accounts api
    }

}
