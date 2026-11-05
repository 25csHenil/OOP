import java.util.Scanner;

public class VendingMachine {
    public enum Coin {
        ONE(1), TWO(2), FIVE(5), TEN(10);
        private final int value;
        Coin(int value) { this.value = value; }
        public int getValue() { return value; }
    }

    public static void main(String[] args) {
        final int SNACK_PRICE = 15;
        int total = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Vending Machine ===");
        System.out.println("Snack Price: 15 INR | Acceptable coins: ONE, TWO, FIVE, TEN");

        // Simulated loop demonstration
        String[] simulatedCoins = {"TEN", "FIVE"};
        System.out.println("Inserting coins sequence: TEN, FIVE");
        for (String coinStr : simulatedCoins) {
            Coin coin = Coin.valueOf(coinStr.toUpperCase());
            int coinVal = switch (coin) {
                case ONE -> 1;
                case TWO -> 2;
                case FIVE -> 5;
                case TEN -> 10;
            };
            total += coinVal;
            System.out.println("Inserted: " + coin + " (" + coinVal + ") | Total so far: " + total);
            if (total >= SNACK_PRICE) {
                break;
            }
        }
        int change = total - SNACK_PRICE;
        System.out.println("Paid. Change: " + change);
    }
}
