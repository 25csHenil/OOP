abstract class Media {
    private final String title;
    Media(String title) { this.title = title; }
    public String getTitle() { return title; }
    abstract double computeLateFee(int daysLate);
}

class Book extends Media {
    Book(String title) { super(title); }
    @Override double computeLateFee(int days) { return days * 2.0; }
}

class Movie extends Media {
    Movie(String title) { super(title); }
    @Override double computeLateFee(int days) { return days * 10.0; }
}

class Magazine extends Media {
    Magazine(String title) { super(title); }
    @Override double computeLateFee(int days) { return days * 1.0; }
}

public class MediaDemo {
    public static void main(String[] args) {
        Media[] batch = {
            new Book("Clean Code"),
            new Movie("Oppenheimer"),
            new Magazine("IEEE Software")
        };

        int days = 5;
        double totalFees = 0;
        for (Media m : batch) {
            double fee = m.computeLateFee(days);
            totalFees += fee;
            System.out.printf("%-15s [%s]: Rs. %.2f\n", m.getTitle(), m.getClass().getSimpleName(), fee);
        }
        System.out.printf("Total Late Fees for %d days: Rs. %.2f\n", days, totalFees);
    }
}
