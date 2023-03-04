package com.bankasarus.customer.controllers;

import com.bankasarus.customer.exceptions.UserNotFoundException;
import com.bankasarus.customer.models.Customer;
import com.bankasarus.customer.services.CustomerDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/customer")
public class CustomerInfoRestController {

    @Autowired
    CustomerDataAccessService customerService;

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("{email}")
    public Customer getCustomerByEmail(@PathVariable("email") String email) {
        return customerService.getCustomerByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User Not Found!!!"));
    }

    @GetMapping("{email}/has-account")
    public boolean isCustomerHavingAccount(@PathVariable("email") String email) {
        return customerService.isCustomerHavingAccount(email);
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
