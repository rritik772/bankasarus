package com.bankasarus.accounts.services;

import com.bankasarus.accounts.models.Account;
import com.bankasarus.accounts.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountDataAccessService {
    @Autowired
    AccountRepository repository;

    public Optional<Account> findAccountById(Long id) {
        return repository.findByAccountId(id);
    }

    public Optional<Account> findAccountByEmail(String email) {
        return repository.findAccountByEmail(email);
    }

}
