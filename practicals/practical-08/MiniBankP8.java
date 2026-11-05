public class MiniBankP8 {
    public static class BankException extends Exception {
        public BankException(String message) { super(message); }
    }

    public static class InsufficientFundsException extends BankException {
        private final long shortfall;
        public InsufficientFundsException(String accNo, long shortfall) {
            super("Insufficient funds in " + accNo + "! Short by: Rs. " + shortfall);
            this.shortfall = shortfall;
        }
        public long getShortfall() { return shortfall; }
    }

    public static class InvalidAmountException extends BankException {
        public InvalidAmountException(String msg) { super(msg); }
    }

    public static class AccountNotFoundException extends BankException {
        public AccountNotFoundException(String msg) { super(msg); }
    }

    public static class Account {
        private final String accountNumber;
        private long balance;

        public Account(String accNo, long balance) {
            this.accountNumber = accNo;
            this.balance = balance;
        }

        public void deposit(long amount) throws InvalidAmountException {
            if (amount <= 0) throw new InvalidAmountException("Deposit amount must be positive!");
            balance += amount;
        }

        public void withdraw(long amount) throws InsufficientFundsException, InvalidAmountException {
            if (amount <= 0) throw new InvalidAmountException("Withdrawal amount must be positive!");
            if (amount > balance) {
                throw new InsufficientFundsException(accountNumber, amount - balance);
            }
            balance -= amount;
        }

        public void transfer(Account to, long amount) throws BankException {
            try {
                this.withdraw(amount);
                to.deposit(amount);
                System.out.println("Transfer of Rs. " + amount + " to " + to.accountNumber + " successful.");
            } catch (BankException e) {
                System.err.println("Transfer aborted: " + e.getMessage());
                throw e;
            } finally {
                System.out.println("Audit: Transfer cycle finalized.");
            }
        }

        public long getBalance() { return balance; }
    }

    public static void main(String[] args) {
        Account a1 = new Account("AC1001", 1000);
        Account a2 = new Account("AC1002", 500);

        System.out.println("=== Testing Insufficient Funds Exception ===");
        try {
            a1.withdraw(5000);
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage() + " | Shortfall: " + e.getShortfall());
            System.out.println("Account balance preserved: Rs. " + a1.getBalance());
        } catch (InvalidAmountException e) {
            System.out.println("Invalid amount: " + e.getMessage());
        }
    }
}
