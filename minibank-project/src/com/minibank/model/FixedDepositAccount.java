package com.minibank.model;
public class FixedDepositAccount extends Account {
    private static final long serialVersionUID = 1L;

    public FixedDepositAccount(String accNo, String owner, long balance) {
        super(accNo, owner, balance);
    }

    @Override public double interestRate() { return 7.0; }
    @Override public boolean canWithdraw(long amount) { return false; } // Locked deposit
}
