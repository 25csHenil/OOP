public class MiniBankP9 {
    public static class Account {
        private final String accountNumber;
        private long balance;

        public Account(String accNo, long balance) {
            this.accountNumber = accNo;
            this.balance = balance;
        }

        public synchronized void deposit(long amount) {
            this.balance += amount;
        }

        public synchronized boolean withdraw(long amount) {
            if (balance >= amount) {
                balance -= amount;
                return true;
            }
            return false;
        }

        public synchronized long getBalance() { return balance; }
    }

    public static class AccountWorker implements Runnable {
        private final Account account;
        private final int times;
        private final long amount;

        public AccountWorker(Account account, int times, long amount) {
            this.account = account;
            this.times = times;
            this.amount = amount;
        }

        @Override public void run() {
            for (int i = 0; i < times; i++) {
                account.deposit(amount);
            }
            System.out.println(Thread.currentThread().getName() + " finished " + times + " deposits.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account sharedAcc = new Account("AC-CONCURRENT-01", 0);
        int threadCount = 10;
        int iterations = 1000;
        Thread[] threads = new Thread[threadCount];

        System.out.println("Launching 10 threads depositing 1 INR x 1000 times into shared account:");
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new AccountWorker(sharedAcc, iterations, 1), "WorkerThread-" + (i + 1));
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("=== Final Balance Audit ===");
        System.out.println("Expected Balance: " + (threadCount * iterations));
        System.out.println("Actual Synchronized Balance: " + sharedAcc.getBalance());
    }
}
