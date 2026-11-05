import java.util.Objects;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point other)) return false;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static void main(String[] args) {
        Point[] points = {
            new Point(1, 2),
            new Point(3, 4),
            new Point(1, 2), // duplicate
            new Point(5, 6),
            new Point(3, 4)  // duplicate
        };

        int distinctCount = 0;
        for (int i = 0; i < points.length; i++) {
            boolean alreadySeen = false;
            for (int j = 0; j < i; j++) {
                if (points[i].equals(points[j])) {
                    alreadySeen = true;
                    break;
                }
            }
            if (!alreadySeen) distinctCount++;
        }
        System.out.println("5 points with 2 repeats -> Distinct: " + distinctCount);
    }
}
