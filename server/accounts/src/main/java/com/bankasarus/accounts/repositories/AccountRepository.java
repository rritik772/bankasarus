package com.bankasarus.accounts.repositories;


import com.bankasarus.accounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountId(Long id);
    Optional<Account> findAccountByEmail(String email);
}
