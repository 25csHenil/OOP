import java.util.Scanner;

public class MiniBank {
    public record BankInfo(String name, String branch) {}

    public enum MenuOption {
        OPEN_ACCOUNT(1),
        DEPOSIT(2),
        WITHDRAW(3),
        TRANSFER(4),
        EXIT(5);

        private final int code;
        MenuOption(int code) { this.code = code; }
        public static MenuOption fromCode(int code) {
            for (MenuOption opt : values()) {
                if (opt.code == code) return opt;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        BankInfo bank = new BankInfo("MiniBank Core Banking", "Anand-Changa Branch");
        System.out.println("==================================================");
        System.out.println(" " + bank.name() + " | " + bank.branch());
        System.out.println("==================================================");

        // Demonstrate menu flow with simulated inputs: 2, 4, 5
        int[] testInputs = {2, 4, 5};
        for (int choice : testInputs) {
            System.out.println("\n1. Open Account\n2. Deposit\n3. Withdraw\n4. Transfer\n5. Exit");
            System.out.println("Enter choice: " + choice);
            MenuOption opt = MenuOption.fromCode(choice);
            if (opt == null) {
                System.out.println("Invalid menu option. Please try again.");
                continue;
            }
            String response = switch (opt) {
                case OPEN_ACCOUNT -> "Open Account — to be implemented in a later lab.";
                case DEPOSIT -> "Deposit — to be implemented in a later lab.";
                case WITHDRAW -> "Withdraw — to be implemented in a later lab.";
                case TRANSFER -> "Transfer — to be implemented in a later lab.";
                case EXIT -> "Thank you for using MiniBank. Goodbye!";
            };
            System.out.println(response);
            if (opt == MenuOption.EXIT) break;
        }
    }
}
