class DivideByZeroException extends Exception {
    public DivideByZeroException(String msg) { super(msg); }
}

public class GuardedCalculator {
    public static double calculate(double a, double b, char op) throws DivideByZeroException {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new DivideByZeroException("Cannot divide " + a + " by zero!");
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Unknown operator: " + op);
        };
    }

    public static void main(String[] args) {
        double[] divisors = {0, 2};
        for (double d : divisors) {
            try {
                System.out.println("Calculating 10 / " + d + ":");
                double res = calculate(10, d, '/');
                System.out.println("Success! Result = " + res);
            } catch (DivideByZeroException e) {
                System.err.println("Caught Custom Exception: " + e.getMessage());
            } finally {
                System.out.println("Attempt logged in audit stream.\n");
            }
        }
    }
}
