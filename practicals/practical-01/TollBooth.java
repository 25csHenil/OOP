import java.util.Scanner;

public class TollBooth {
    public record Vehicle(String number, String type) {}

    public static void main(String[] args) {
        int totalToll = 0;
        int bikeCount = 0;
        int carCount = 0;
        int truckCount = 0;

        Vehicle[] vehicles = {
            new Vehicle("GJ-01-AB-1234", "car"),
            new Vehicle("GJ-06-CD-5678", "car"),
            new Vehicle("GJ-23-EF-9012", "bike")
        };

        System.out.println("=== Automated Toll Booth ===");
        for (Vehicle v : vehicles) {
            int fee = switch (v.type().toLowerCase()) {
                case "bike" -> { bikeCount++; yield 20; }
                case "car" -> { carCount++; yield 50; }
                case "truck" -> { truckCount++; yield 150; }
                default -> 0;
            };
            totalToll += fee;
            System.out.println("Vehicle: " + v.number() + " [" + v.type() + "] | Toll: " + fee);
        }

        String mostFrequent = "car";
        if (bikeCount > carCount && bikeCount > truckCount) mostFrequent = "bike";
        else if (truckCount > carCount && truckCount > bikeCount) mostFrequent = "truck";

        System.out.println("Total toll: " + totalToll);
        System.out.println("Most frequent: " + mostFrequent);
    }
}
