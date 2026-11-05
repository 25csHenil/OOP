package com.minibank.model;
import com.minibank.model.annotation.*;
import com.minibank.exception.*;
import java.io.Serializable;
import java.util.Objects;

public abstract class Account implements Transactable, InterestBearing, Comparable<Account>, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    protected final String accountNumber;

    @MaxLength(50)
    protected String ownerName;

    @Positive
    protected long balance;

    protected boolean active;

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.active = true;
    }

    public abstract boolean canWithdraw(long amount);

    @Override
    public synchronized void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Deposit must be positive: " + amount);
        this.balance += amount;
    }

    @Override
    public synchronized boolean withdraw(long amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Withdrawal must be positive: " + amount);
        if (!canWithdraw(amount)) {
            long shortfall = amount - balance;
            throw new InsufficientFundsException(accountNumber, Math.max(0, shortfall));
        }
        this.balance -= amount;
        return true;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public synchronized long getBalance() { return balance; }
    public boolean isActive() { return active; }

    @Override
    public int compareTo(Account other) {
        return this.accountNumber.compareTo(other.accountNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account acc)) return false;
        return Objects.equals(accountNumber, acc.accountNumber);
    }

    @Override
    public int hashCode() { return Objects.hash(accountNumber); }

    @Override
    public String toString() {
        return String.format("%-10s | %-16s | Rs. %8d | %s",
            accountNumber, ownerName, balance, (active ? "ACTIVE" : "INACTIVE"));
    }
}
