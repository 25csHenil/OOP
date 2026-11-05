public class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;
    private static long accountCounter = 0;

    private static synchronized String generateAccountNumber() {
        accountCounter++;
        return String.format("AC%04d", accountCounter);
    }

    public Account(String ownerName, long openingBalance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = Math.max(0, openingBalance);
        this.active = true;
    }

    public Account(String ownerName) {
        this(ownerName, 0); // constructor chaining
    }

    public void deposit(long amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(long amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }
    public boolean isActive() { return active; }

    public static void main(String[] args) {
        Account a1 = new Account("Riya", 5000);
        Account a2 = new Account("Henil", 10000);
        Account a3 = new Account("Aarav");

        Account[] accounts = {a1, a2, a3};
        System.out.println("=== MiniBank Practical 2 Account Demonstration ===");
        a1.deposit(2000);
        System.out.println("a1 after deposit(2000): " + a1.getBalance());
        boolean w1 = a1.withdraw(3000);
        System.out.println("a1 withdraw(3000): " + w1 + " | Balance: " + a1.getBalance());
        boolean w2 = a1.withdraw(10000);
        System.out.println("a1 withdraw(10000): " + w2 + " | Balance: " + a1.getBalance());

        for (Account acc : accounts) {
            System.out.println("Acc: " + acc.getAccountNumber() + " | Owner: " + acc.getOwnerName() + " | Balance: " + acc.getBalance());
        }
    }
}
