import java.util.List;

@FunctionalInterface
interface DiscountRule {
    double apply(double price);
}

public class DiscountEngineDemo {
    public static void main(String[] args) {
        List<Double> prices = List.of(100.0, 500.0, 1200.0, 2500.0);

        DiscountRule festivalDiscount = p -> p > 1000 ? p * 0.80 : p * 0.90;
        DiscountRule flatTenPercent = p -> p * 0.90;

        System.out.println("Original Prices: " + prices);
        System.out.println("After Festival Discount (20% above 1000):");
        prices.forEach(p -> System.out.printf("  Rs. %.2f -> Rs. %.2f\n", p, festivalDiscount.apply(p)));
    }
}
