package com.bankasarus.admin.controllers;

import com.bankasarus.admin.services.AdminCustomerDataAccessService;
import com.bankasarus.customer.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/customer")
public class CustomerRestControllers {

    @Autowired
    AdminCustomerDataAccessService service;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(service.getAllCustomer());
    }

    @GetMapping("{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {

        return service.getCustomerByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @GetMapping("created-before/{date}")
    public ResponseEntity<List<Customer>> getCustomersCreatedBefore(@PathVariable String date) {
        return ResponseEntity.ok(service.getCustomersCreatedBefore(Date.valueOf(date)));
    }

    @GetMapping("created-after/{date}")
    public ResponseEntity<List<Customer>> getCustomersCreatedAfter(@PathVariable String date) {
        return ResponseEntity.ok(service.getCustomersCreatedAfter(Date.valueOf(date)));
    }

    @GetMapping("birth-before/{date}")
    public ResponseEntity<List<Customer>> getCustomersBirthBefore(@PathVariable String date) {
        return ResponseEntity.ok(service.getCustomersBirthDateBefore(Date.valueOf(date)));
    }

    @GetMapping("birth-after/{date}")
    public ResponseEntity<List<Customer>> getCustomersBirthAfter(@PathVariable String date) {
        return ResponseEntity.ok(service.getCustomersBirthDateAfter(Date.valueOf(date)));
    }

    @GetMapping("birthday")
    public ResponseEntity<List<Customer>> getCustomersHavingBirthdayToday() {
        return ResponseEntity.ok(service.getCustomersHavingBirthdayToday());
    }
}
