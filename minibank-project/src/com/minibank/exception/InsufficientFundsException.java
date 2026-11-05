package com.minibank.exception;
public class InsufficientFundsException extends BankException {
    private final long shortfall;
    public InsufficientFundsException(String accNo, long shortfall) {
        super("Insufficient funds in " + accNo + "! Short by: Rs. " + shortfall);
        this.shortfall = shortfall;
    }
    public long getShortfall() { return shortfall; }
}
