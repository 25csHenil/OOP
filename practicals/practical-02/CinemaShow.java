public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    public CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

    public CinemaShow(String title) {
        this(title, 100); // constructor chaining
    }

    public boolean book(int n) {
        if (n > 0 && n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        return false;
    }

    public void cancel(int n) {
        if (n > 0) {
            seatsAvailable = Math.min(capacity, seatsAvailable + n);
        }
    }

    public int getSeatsAvailable() { return seatsAvailable; }
    public static int getTotalBooked() { return totalBooked; }

    public static void main(String[] args) {
        CinemaShow show = new CinemaShow("Inception 2", 50);
        System.out.println("Booking 20 seats: " + show.book(20) + " | Available: " + show.getSeatsAvailable());
        System.out.println("Booking 40 seats (exceeds): " + show.book(40) + " | Available: " + show.getSeatsAvailable());
        show.cancel(5);
        System.out.println("Cancelled 5 seats | Available: " + show.getSeatsAvailable());
        System.out.println("Total Booked across system: " + CinemaShow.getTotalBooked());
    }
}
