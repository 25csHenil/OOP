package com.minibank.main;
import com.minibank.model.*;
import com.minibank.service.BankService;
import com.minibank.repository.Repository;
import com.minibank.util.StatementFormatter;

public class MiniBankApp {
    public static void main(String[] args) {
        BankInfo info = new BankInfo("MiniBank Enterprise Core", "CHARUSAT University Campus", "MBIN0001008");
        System.out.println("==================================================================");
        System.out.println(" " + info.name() + " | " + info.branch() + " | IFSC: " + info.ifsc());
        System.out.println("==================================================================");

        Repository<Account> repo = new Repository<>();
        BankService bankService = new BankService(repo);

        try {
            System.out.println("\n1. [OPEN ACCOUNTS]");
            Account a1 = bankService.openSavingsAccount("AC1001", "Riya Patel", 25000, 1000);
            Account a2 = bankService.openCurrentAccount("AC1002", "Henil Trivedi", 50000, 20000);
            System.out.println("  Opened: " + a1);
            System.out.println("  Opened: " + a2);

            System.out.println("\n2. [DEPOSIT & WITHDRAWAL TRANSACTIONS]");
            bankService.deposit("AC1001", 10000);
            System.out.println("  Deposited Rs. 10000 into AC1001. New balance: Rs. " + a1.getBalance());
            bankService.withdraw("AC1002", 15000);
            System.out.println("  Withdrew Rs. 15000 from AC1002. New balance: Rs. " + a2.getBalance());

            System.out.println("\n3. [DEADLOCK-SAFE INTER-ACCOUNT TRANSFER]");
            bankService.transfer("AC1002", "AC1001", 5000);
            System.out.println("  Transferred Rs. 5000 from AC1002 to AC1001.");

            System.out.println("\n4. [ACCOUNT STATEMENTS]");
            System.out.println(StatementFormatter.buildStatement(a1));
            System.out.println(StatementFormatter.buildStatement(a2));

            System.out.println("5. [ALL ACCOUNTS SORTED BY BALANCE]");
            for (Account a : bankService.listAccountsSortedByBalance()) {
                System.out.println("  " + a);
            }

            System.out.println("\nMiniBank Application Run Completed Successfully.");
        } catch (Exception e) {
            System.err.println("Banking Error: " + e.getMessage());
        }
    }
}
