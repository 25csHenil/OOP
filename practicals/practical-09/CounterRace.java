class Counter {
    private int count = 0;
    public void incrementUnsync() { count++; }
    public synchronized void incrementSync() { count++; }
    public int getCount() { return count; }
    public void reset() { count = 0; }
}

public class CounterRace {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();

        // 1. Unsynchronized Race Condition
        Thread[] unsyncThreads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            unsyncThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) c.incrementUnsync();
            });
            unsyncThreads[i].start();
        }
        for (Thread t : unsyncThreads) t.join();
        System.out.println("Unsynchronized Count (Expected 10000, Actual Lost Updates): " + c.getCount());

        // 2. Synchronized Fix
        c.reset();
        Thread[] syncThreads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            syncThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) c.incrementSync();
            });
            syncThreads[i].start();
        }
        for (Thread t : syncThreads) t.join();
        System.out.println("Synchronized Count (Deterministic): " + c.getCount());
    }
}
