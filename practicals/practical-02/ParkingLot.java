public class ParkingLot {
    private int twoWheelers = 0;
    private int fourWheelers = 0;
    private final int twoCap;
    private final int fourCap;
    private static long revenue = 0;

    public ParkingLot(int twoCap, int fourCap) {
        this.twoCap = twoCap;
        this.fourCap = fourCap;
    }

    public void park(String type) {
        if ("two".equalsIgnoreCase(type)) {
            if (twoWheelers < twoCap) {
                twoWheelers++;
                revenue += 20;
                System.out.println("Parked 2-Wheeler. Occupancy: " + twoWheelers + "/" + twoCap);
            } else {
                System.out.println("2-Wheeler parking Full! Rejected.");
            }
        } else if ("four".equalsIgnoreCase(type)) {
            if (fourWheelers < fourCap) {
                fourWheelers++;
                revenue += 40;
                System.out.println("Parked 4-Wheeler. Occupancy: " + fourWheelers + "/" + fourCap);
            } else {
                System.out.println("4-Wheeler parking Full! Rejected.");
            }
        }
    }

    public void leave(String type) {
        if ("two".equalsIgnoreCase(type) && twoWheelers > 0) twoWheelers--;
        else if ("four".equalsIgnoreCase(type) && fourWheelers > 0) fourWheelers--;
    }

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(2, 2);
        lot.park("two");
        lot.park("two");
        lot.park("two"); // reject
        lot.park("four");
        lot.park("four");
        lot.park("four"); // reject
        System.out.println("Total Parking Revenue: " + revenue + " INR");
    }
}
