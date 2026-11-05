class AccountLock {
    final String id;
    AccountLock(String id) { this.id = id; }
}

public class DeadlockDemo {
    public static void safeTransfer(AccountLock a, AccountLock b) {
        AccountLock first = a.id.compareTo(b.id) < 0 ? a : b;
        AccountLock second = (first == a) ? b : a;

        synchronized (first) {
            System.out.println(Thread.currentThread().getName() + " locked first: " + first.id);
            synchronized (second) {
                System.out.println(Thread.currentThread().getName() + " locked second: " + second.id + " -> Transfer DONE.");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AccountLock accA = new AccountLock("AC0001");
        AccountLock accB = new AccountLock("AC0002");

        Thread t1 = new Thread(() -> safeTransfer(accA, accB), "TransferThread-1");
        Thread t2 = new Thread(() -> safeTransfer(accB, accA), "TransferThread-2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Consistent Lock Ordering prevented deadlock successfully!");
    }
}
