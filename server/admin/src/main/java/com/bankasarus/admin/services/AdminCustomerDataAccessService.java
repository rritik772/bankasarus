package com.bankasarus.admin.services;

import com.bankasarus.admin.repositories.AdminCustomerRepository;
import com.bankasarus.customer.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminCustomerDataAccessService {

    @Autowired
    AdminCustomerRepository repository;

    public List<Customer> getAllCustomer() {
        return repository.getAllCustomers();
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return repository.getCustomerByEmail(email);
    }

    public List<Customer> getCustomersCreatedAfter(Date date) {
        return repository.getCustomerByCreatedOnAfter(date);
    }

    public List<Customer> getCustomersCreatedBefore(Date date) {
        return repository.getCustomerByCreatedOnBefore(date);
    }

    public List<Customer> getCustomersBirthDateAfter(Date date) {
        return repository.getCustomerByBirthDateAfter(date);
    }

    public List<Customer> getCustomersBirthDateBefore(Date date) {
        return repository.getCustomerByBirthDateBefore(date);
    }

    public List<Customer> getCustomersHavingBirthdayToday() {
        return repository.getCustomersHavingBirthdayToday();
    }
}
