package com.minibank.util;
import com.minibank.model.Account;
public class StatementFormatter {
    public static String buildStatement(Account account) {
        StringBuilder sb = new StringBuilder();
        sb.append("==================================================\n");
        sb.append("          MINIBANK ACCOUNT STATEMENT\n");
        sb.append("==================================================\n");
        sb.append("Account Number : ").append(account.getAccountNumber()).append("\n");
        sb.append("Account Holder : ").append(account.getOwnerName()).append("\n");
        sb.append("Account Type   : ").append(account.getClass().getSimpleName()).append("\n");
        sb.append("Current Balance: Rs. ").append(account.getBalance()).append("\n");
        sb.append("Interest Rate  : ").append(account.interestRate()).append("% p.a.\n");
        sb.append("Status         : ").append(account.isActive() ? "ACTIVE" : "FROZEN").append("\n");
        sb.append("==================================================\n");
        return sb.toString();
    }
}
