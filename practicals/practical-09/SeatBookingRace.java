class CinemaTicketBooth {
    private int seatsLeft = 5;

    public boolean bookSeatUnsync(String user) {
        if (seatsLeft > 0) {
            try { Thread.sleep(10); } catch (InterruptedException ignored) {}
            seatsLeft--;
            System.out.println(user + " successfully booked. Seats left: " + seatsLeft);
            return true;
        }
        return false;
    }

    public synchronized boolean bookSeatSync(String user) {
        if (seatsLeft > 0) {
            seatsLeft--;
            System.out.println(user + " successfully booked. Seats left: " + seatsLeft);
            return true;
        }
        return false;
    }
}

public class SeatBookingRace {
    public static void main(String[] args) throws InterruptedException {
        CinemaTicketBooth booth = new CinemaTicketBooth();
        System.out.println("Starting 10 concurrent threads for 5 seats (Synchronized Booking):");
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            String name = "User-" + (i + 1);
            threads[i] = new Thread(() -> booth.bookSeatSync(name));
            threads[i].start();
        }
        for (Thread t : threads) t.join();
    }
}
