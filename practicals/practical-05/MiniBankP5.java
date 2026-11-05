public class MiniBankP5 {
    public static abstract class Account {
        protected final String accountNumber;
        protected final String ownerName;
        protected long balance;

        public Account(String accountNumber, String ownerName, long balance) {
            this.accountNumber = accountNumber;
            this.ownerName = ownerName;
            this.balance = balance;
        }

        public abstract double interestRate();
        public abstract boolean canWithdraw(long amount);

        public void deposit(long amount) { if (amount > 0) balance += amount; }
        public boolean withdraw(long amount) {
            if (canWithdraw(amount)) {
                balance -= amount;
                return true;
            }
            return false;
        }

        public String getAccountNumber() { return accountNumber; }
        public String getOwnerName() { return ownerName; }
        public long getBalance() { return balance; }
    }

    public static class SavingsAccount extends Account {
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

    public static class CurrentAccount extends Account {
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

    public static class FixedDepositAccount extends Account {
        public FixedDepositAccount(String accNo, String owner, long balance) {
            super(accNo, owner, balance);
        }
        @Override public double interestRate() { return 7.0; }
        @Override public boolean canWithdraw(long amount) {
            return false; // locked deposit
        }
    }

    public static void main(String[] args) {
        Account[] bankAccounts = {
            new SavingsAccount("SA1001", "Riya", 5000, 1000),
            new CurrentAccount("CA2001", "Henil", 2000, 5000),
            new FixedDepositAccount("FD3001", "Aarav", 50000)
        };

        System.out.println("=== Polymorphic Interest Rates ===");
        for (Account acc : bankAccounts) {
            System.out.printf("%s [%s] -> Interest Rate: %.1f%%\n",
                acc.getAccountNumber(), acc.getClass().getSimpleName(), acc.interestRate());

            // Pattern matching instanceof
            if (acc instanceof CurrentAccount ca) {
                System.out.println("   -> Special Corporate handling enabled for Current Account: " + ca.getOwnerName());
            }
        }
    }
}
