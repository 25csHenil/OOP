interface Urgent {} // marker interface

@FunctionalInterface
interface Notifier {
    void send(String message);
}

class EmailNotifier implements Notifier {
    @Override public void send(String msg) { System.out.println("[EMAIL] " + msg); }
}

class UrgentSmsNotifier implements Notifier, Urgent {
    @Override public void send(String msg) { System.out.println("[SMS ALERT] " + msg); }
}

public class NotifierDemo {
    public static void main(String[] args) {
        Notifier[] notifiers = {
            new EmailNotifier(),
            new UrgentSmsNotifier(),
            msg -> System.out.println("[PUSH NOTIFICATION] " + msg)
        };

        String message = "Security Warning: Login from unauthorized device!";
        System.out.println("Broadcasting notification across channel network:");

        for (Notifier n : notifiers) {
            n.send(message);
            if (n instanceof Urgent) {
                System.out.println("--> High Priority sender detected! Repeating broadcast:");
                n.send(message);
            }
        }
    }
}
