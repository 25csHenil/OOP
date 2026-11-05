public class ArraySumRace {
    private static long totalSum = 0;
    private static synchronized void addToSum(long val) { totalSum += val; }

    public static void main(String[] args) throws InterruptedException {
        int[] numbers = new int[10000];
        for (int i = 0; i < numbers.length; i++) numbers[i] = 1;

        Thread t1 = new Thread(() -> {
            long s = 0;
            for (int i = 0; i < 5000; i++) s += numbers[i];
            addToSum(s);
        });
        Thread t2 = new Thread(() -> {
            long s = 0;
            for (int i = 5000; i < 10000; i++) s += numbers[i];
            addToSum(s);
        });

        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println("Total Multi-threaded Sum: " + totalSum);
    }
}
