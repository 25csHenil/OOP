package com.minibank.service;
import com.minibank.model.*;
import com.minibank.repository.Repository;
import com.minibank.exception.*;
import com.minibank.util.Validator;
import java.util.*;

public class BankService {
    private final Repository<Account> repository;

    public BankService(Repository<Account> repository) {
        this.repository = repository;
    }

    public Account openSavingsAccount(String accNo, String owner, long initialDeposit, long minBalance) throws BankException {
        if (repository.findById(accNo) != null) throw new BankException("Account already exists: " + accNo);
        if (initialDeposit < minBalance) throw new InvalidAmountException("Initial deposit below minimum balance!");
        SavingsAccount acc = new SavingsAccount(accNo, owner, initialDeposit, minBalance);
        return repository.save(acc);
    }

    public Account openCurrentAccount(String accNo, String owner, long initialDeposit, long overdraftLimit) throws BankException {
        if (repository.findById(accNo) != null) throw new BankException("Account already exists: " + accNo);
        CurrentAccount acc = new CurrentAccount(accNo, owner, initialDeposit, overdraftLimit);
        return repository.save(acc);
    }

    public void deposit(String accNo, long amount) throws BankException {
        Account acc = repository.findById(accNo);
        if (acc == null) throw new AccountNotFoundException(accNo);
        acc.deposit(amount);
    }

    public void withdraw(String accNo, long amount) throws BankException {
        Account acc = repository.findById(accNo);
        if (acc == null) throw new AccountNotFoundException(accNo);
        acc.withdraw(amount);
    }

    public boolean transfer(String fromAcc, String toAcc, long amount) throws BankException {
        Account a1 = repository.findById(fromAcc);
        Account a2 = repository.findById(toAcc);
        if (a1 == null) throw new AccountNotFoundException(fromAcc);
        if (a2 == null) throw new AccountNotFoundException(toAcc);

        // Deadlock-free locking hierarchy
        Account first = a1.getAccountNumber().compareTo(a2.getAccountNumber()) < 0 ? a1 : a2;
        Account second = (first == a1) ? a2 : a1;

        synchronized (first) {
            synchronized (second) {
                a1.withdraw(amount);
                a2.deposit(amount);
                return true;
            }
        }
    }

    public List<Account> listAccountsSortedByBalance() {
        List<Account> list = repository.findAll();
        list.sort((a, b) -> Long.compare(b.getBalance(), a.getBalance()));
        return list;
    }

    public Account getAccount(String accNo) { return repository.findById(accNo); }
}
