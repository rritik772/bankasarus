package com.bankasarus.admin.controllers;

import com.bankasarus.admin.services.CustomerDataAccessService;
import com.bankasarus.customer.models.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.MessageFormat;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/customer")
public class CustomerRestControllers {

    @Autowired
    CustomerDataAccessService service;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        log.info("getAllCustomer called.");

        return ResponseEntity.ok(service.getAllCustomer());
    }

    @GetMapping("{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        log.info(MessageFormat.format("getCustomerByEmail called with email {0}", email));

        return service.getCustomerByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @GetMapping("created-before/{date}")
    public ResponseEntity<List<Customer>> getCustomersCreatedBefore(@PathVariable String date) {
        log.info(MessageFormat.format("getCustomersCreatedBefore called with date {0}", date));

        return ResponseEntity.ok(service.getCustomersCreatedBefore(Date.valueOf(date)));
    }

    @GetMapping("created-after/{date}")
    public ResponseEntity<List<Customer>> getCustomersCreatedAfter(@PathVariable String date) {
        log.info(MessageFormat.format("getCustomersCreatedAfter called with date {0}", date));

        return ResponseEntity.ok(service.getCustomersCreatedAfter(Date.valueOf(date)));
    }

    @GetMapping("birth-before/{date}")
    public ResponseEntity<List<Customer>> getCustomersBirthBefore(@PathVariable String date) {
        log.info(MessageFormat.format("getCustomersBirthBefore called with date {0}", date));

        return ResponseEntity.ok(service.getCustomersBirthDateBefore(Date.valueOf(date)));
    }

    @GetMapping("birth-after/{date}")
    public ResponseEntity<List<Customer>> getCustomersBirthAfter(@PathVariable String date) {
        log.info(MessageFormat.format("getCustomersBirthAfter called with date {0}", date));

        return ResponseEntity.ok(service.getCustomersBirthDateAfter(Date.valueOf(date)));
    }

    @GetMapping("birthday")
    public ResponseEntity<List<Customer>> getCustomersHavingBirthdayToday() {
        log.info("getCustomersHavingBirthdayToday called");

        return ResponseEntity.ok(service.getCustomersHavingBirthdayToday());
    }
}
