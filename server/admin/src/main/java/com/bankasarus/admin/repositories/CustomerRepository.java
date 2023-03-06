package com.bankasarus.admin.repositories;


import com.bankasarus.customer.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * CustomerRepository
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    String allCustomers = "SELECT * FROM customer";
    String birthdayToday = "SELECT * FROM customer where birth_date = CURRENT_DATE";

    @Query(value = allCustomers, nativeQuery = true)
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerByEmail(String email);
    List<Customer> getCustomerByCreatedOnAfter(Date date);
    List<Customer> getCustomerByCreatedOnBefore(Date date);
    List<Customer> getCustomerByBirthDateAfter(Date date);
    List<Customer> getCustomerByBirthDateBefore(Date date);

    @Query(value = birthdayToday, nativeQuery = true)
    List<Customer> getCustomersHavingBirthdayToday();
}
