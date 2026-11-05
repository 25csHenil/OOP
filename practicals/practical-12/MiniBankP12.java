import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MiniBankP12 {
    public static class Account implements Comparable<Account> {
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
        public long getBalance() { return balance; }

        @Override
        public int compareTo(Account other) {
            return this.accountNumber.compareTo(other.accountNumber);
        }

        @Override
        public String toString() {
            return accountNumber + " | " + ownerName + " | Rs. " + balance;
        }
    }

    public static class AccountStore {
        private final Map<String, Account> accounts = new HashMap<>();
        private final List<String> txHistory = new ArrayList<>();

        public void add(Account acc) {
            accounts.put(acc.getAccountNumber(), acc);
            txHistory.add("Added account: " + acc.getAccountNumber());
        }

        public Account get(String accNo) { return accounts.get(accNo); }
        public Collection<Account> getAll() { return accounts.values(); }

        public List<Account> sortedByBalance() {
            List<Account> list = new ArrayList<>(accounts.values());
            list.sort((a, b) -> Long.compare(b.getBalance(), a.getBalance())); // highest balance first
            return list;
        }
    }

    public static void main(String[] args) {
        AccountStore store = new AccountStore();
        store.add(new Account("AC0001", "Riya", 5000));
        store.add(new Account("AC0002", "Henil", 2000));
        store.add(new Account("AC0003", "Aarav", 9000));

        System.out.println("Lookup by ID AC0002: " + store.get("AC0002"));

        System.out.println("\nAccounts Sorted by Balance (Highest First):");
        for (Account acc : store.sortedByBalance()) {
            System.out.println("  " + acc);
        }
    }
}
