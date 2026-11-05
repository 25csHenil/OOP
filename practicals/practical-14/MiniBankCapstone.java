import java.util.*;
import java.util.concurrent.*;

public class MiniBankCapstone {
    public record BankInfo(String name, String branch) {}

    public static class Account {
        private final String accountNumber;
        private final String ownerName;
        private long balance;

        public Account(String accNo, String owner, long balance) {
            this.accountNumber = accNo;
            this.ownerName = owner;
            this.balance = balance;
        }
        public String getAccountNumber() { return accountNumber; }
        public String getOwnerName() { return ownerName; }
        public synchronized long getBalance() { return balance; }
        public synchronized void deposit(long amt) { balance += amt; }
        public synchronized boolean withdraw(long amt) {
            if (balance >= amt) { balance -= amt; return true; }
            return false;
        }
    }

    public static class BankService {
        private final Map<String, Account> accounts = new ConcurrentHashMap<>();

        public void openAccount(String accNo, String owner, long balance) {
            accounts.put(accNo, new Account(accNo, owner, balance));
        }

        public void deposit(String accNo, long amt) {
            Account acc = accounts.get(accNo);
            if (acc != null) acc.deposit(amt);
        }

        public boolean withdraw(String accNo, long amt) {
            Account acc = accounts.get(accNo);
            return acc != null && acc.withdraw(amt);
        }

        public boolean transfer(String from, String to, long amt) {
            Account a1 = accounts.get(from);
            Account a2 = accounts.get(to);
            if (a1 == null || a2 == null) return false;

            Account first = a1.accountNumber.compareTo(a2.accountNumber) < 0 ? a1 : a2;
            Account second = (first == a1) ? a2 : a1;

            synchronized (first) {
                synchronized (second) {
                    if (a1.withdraw(amt)) {
                        a2.deposit(amt);
                        return true;
                    }
                }
            }
            return false;
        }

        public Collection<Account> listAccounts() { return accounts.values(); }
    }

    public static void main(String[] args) {
        BankInfo info = new BankInfo("MiniBank Enterprise System", "CHARUSAT University Campus");
        System.out.println("============================================================");
        System.out.println(" " + info.name() + " | " + info.branch());
        System.out.println("============================================================");

        BankService service = new BankService();
        service.openAccount("AC1001", "Riya Patel", 15000);
        service.openAccount("AC1002", "Henil Trivedi", 25000);

        System.out.println("\nExecuting Banking Services:");
        service.deposit("AC1001", 5000);
        System.out.println("Deposit Rs. 5000 into AC1001 successful.");
        boolean w = service.withdraw("AC1002", 3000);
        System.out.println("Withdraw Rs. 3000 from AC1002: " + w);

        boolean tr = service.transfer("AC1002", "AC1001", 2000);
        System.out.println("Deadlock-safe Transfer Rs. 2000 from AC1002 to AC1001: " + tr);

        System.out.println("\nFinal Account Statements:");
        for (Account acc : service.listAccounts()) {
            System.out.printf("  Acc: %-8s | Owner: %-15s | Balance: Rs. %d\n",
                acc.getAccountNumber(), acc.getOwnerName(), acc.getBalance());
        }
        System.out.println("\nMiniBank Milestone 3 Capstone successfully verified.");
    }
}
