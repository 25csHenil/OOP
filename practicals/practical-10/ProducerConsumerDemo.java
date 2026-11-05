import java.util.LinkedList;
import java.util.Queue;

class TransactionBuffer {
    private final Queue<String> queue = new LinkedList<>();
    private final int capacity = 3;

    public synchronized void produce(String tx) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(tx);
        System.out.println("PRODUCED: " + tx + " | Buffer size: " + queue.size());
        notifyAll();
    }

    public synchronized String consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        String tx = queue.poll();
        System.out.println("CONSUMED: " + tx + " | Buffer size: " + queue.size());
        notifyAll();
        return tx;
    }
}

public class ProducerConsumerDemo {
    public static void main(String[] args) throws InterruptedException {
        TransactionBuffer buffer = new TransactionBuffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.produce("Tx-" + i);
                    Thread.sleep(20);
                }
            } catch (InterruptedException ignored) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.consume();
                    Thread.sleep(40);
                }
            } catch (InterruptedException ignored) {}
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
