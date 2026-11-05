package com.minibank.model;
public class CurrentAccount extends Account {
    private static final long serialVersionUID = 1L;
    private final long overdraftLimit;

    public CurrentAccount(String accNo, String owner, long balance, long overdraftLimit) {
        super(accNo, owner, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override public double interestRate() { return 0.0; }
    @Override public boolean canWithdraw(long amount) {
        return (balance - amount) >= -overdraftLimit;
    }
}
