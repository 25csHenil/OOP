package com.minibank.model;
public class SavingsAccount extends Account {
    private static final long serialVersionUID = 1L;
    private final long minBalance;

    public SavingsAccount(String accNo, String owner, long balance, long minBalance) {
        super(accNo, owner, balance);
        this.minBalance = minBalance;
    }

    @Override public double interestRate() { return 4.0; }
    @Override public boolean canWithdraw(long amount) {
        return (balance - amount) >= minBalance;
    }
}
