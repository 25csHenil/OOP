public class Thermostat {
    private String location;
    private int temperature;
    private static final int MIN = 16;
    private static final int MAX = 30;
    private static int activeCount = 0;

    public Thermostat(String location, int startTemp) {
        this.location = location;
        if (startTemp >= MIN && startTemp <= MAX) {
            this.temperature = startTemp;
        } else {
            this.temperature = 22;
        }
        activeCount++;
    }

    public Thermostat(String location) {
        this(location, 22); // constructor chaining
    }

    public void raise() {
        if (temperature < MAX) {
            temperature++;
            System.out.println(location + " temp raised to: " + temperature);
        } else {
            System.out.println(location + ": Already at maximum (" + MAX + ")");
        }
    }

    public void lower() {
        if (temperature > MIN) {
            temperature--;
            System.out.println(location + " temp lowered to: " + temperature);
        } else {
            System.out.println(location + ": Already at minimum (" + MIN + ")");
        }
    }

    public int getTemperature() { return temperature; }
    public String getLocation() { return location; }
    public static int getActiveCount() { return activeCount; }

    public static void main(String[] args) {
        Thermostat t1 = new Thermostat("Living Room", 25);
        Thermostat t2 = new Thermostat("Master Bedroom");

        System.out.println("Raising t1 10 times:");
        for (int i = 0; i < 10; i++) t1.raise();

        System.out.println("\nLowering t1 20 times:");
        for (int i = 0; i < 20; i++) t1.lower();

        System.out.println("\nActive thermostats count: " + Thermostat.getActiveCount());
    }
}
