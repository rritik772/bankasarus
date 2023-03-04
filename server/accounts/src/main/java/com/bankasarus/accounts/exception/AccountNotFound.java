package com.bankasarus.accounts.exception;

public class AccountNotFound extends RuntimeException {
    public AccountNotFound(String msg) {
        super(msg);
    }
}
