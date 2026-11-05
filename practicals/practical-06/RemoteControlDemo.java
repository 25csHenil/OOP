interface Switchable {
    void on();
    void off();
    default void toggle() {
        System.out.println(this.getClass().getSimpleName() + " toggled.");
    }
}

class Fan implements Switchable {
    @Override public void on() { System.out.println("Fan turned ON."); }
    @Override public void off() { System.out.println("Fan turned OFF."); }
}

class Light implements Switchable {
    @Override public void on() { System.out.println("Light turned ON."); }
    @Override public void off() { System.out.println("Light turned OFF."); }
}

@FunctionalInterface
interface OperationPolicy {
    boolean isAllowed(Switchable device, int hour);
}

public class RemoteControlDemo {
    public static void main(String[] args) {
        Switchable[] devices = { new Fan(), new Light() };
        for (Switchable d : devices) {
            d.toggle();
        }

        // Anonymous class
        OperationPolicy nightPolicy = new OperationPolicy() {
            @Override
            public boolean isAllowed(Switchable device, int hour) {
                return hour >= 18 && hour <= 23;
            }
        };

        // Lambda expression
        OperationPolicy dayPolicy = (device, hour) -> hour >= 6 && hour < 18;

        System.out.println("Night Policy at 20:00 (Anonymous): " + nightPolicy.isAllowed(devices[0], 20));
        System.out.println("Day Policy at 20:00 (Lambda): " + dayPolicy.isAllowed(devices[1], 20));
    }
}
