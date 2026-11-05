import java.util.concurrent.*;

public class MiniBankP10 {
    public static class TransactionProcessor {
        private final ExecutorService executor;

        public TransactionProcessor(int poolSize) {
            this.executor = Executors.newFixedThreadPool(poolSize);
        }

        public void submit(Runnable task) {
            executor.execute(task);
        }

        public void stop() {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }
    }

    public static class Account {
        private final String accountNumber;
        private long balance;

        public Account(String accNo, long balance) {
            this.accountNumber = accNo;
            this.balance = balance;
        }

        public String getAccountNumber() { return accountNumber; }
        public synchronized long getBalance() { return balance; }

        public void safeTransfer(Account target, long amount) {
            // Consistent Global Lock Ordering by Account Number
            Account first = this.accountNumber.compareTo(target.accountNumber) < 0 ? this : target;
            Account second = (first == this) ? target : this;

            synchronized (first) {
                synchronized (second) {
                    if (this.balance >= amount) {
                        this.balance -= amount;
                        target.balance += amount;
                        System.out.println(Thread.currentThread().getName() + ": Transferred Rs. " + amount + " from " + this.accountNumber + " to " + target.accountNumber);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TransactionProcessor processor = new TransactionProcessor(4);
        Account a = new Account("AC0001", 10000);
        Account b = new Account("AC0002", 10000);

        System.out.println("Submitting batch transfers concurrently to TransactionProcessor pool:");
        processor.submit(() -> a.safeTransfer(b, 500));
        processor.submit(() -> b.safeTransfer(a, 300));
        processor.submit(() -> a.safeTransfer(b, 200));

        processor.stop();
        System.out.println("Batch processing completed. Final Balances:");
        System.out.println("  " + a.getAccountNumber() + ": Rs. " + a.getBalance());
        System.out.println("  " + b.getAccountNumber() + ": Rs. " + b.getBalance());
    }
}
