package com.bankasarus.customer.services;

import com.bankasarus.customer.models.Customer;
import com.bankasarus.customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerDataAccessService {
    @Autowired
    CustomerRepository customerRepository;

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public boolean isCustomerHavingAccount(String email) {
        Optional<Customer> c = customerRepository.findByEmail(email);
        return c.map(Customer::isDeleted).orElse(false);
    }
}
