package com.minibank.exception;
public class AccountNotFoundException extends BankException {
    public AccountNotFoundException(String accNo) { super("Account not found: " + accNo); }
}
