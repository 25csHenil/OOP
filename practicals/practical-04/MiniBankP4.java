import java.util.regex.Pattern;

public class MiniBankP4 {
    public static class Validator {
        private static final Pattern MOBILE_PAT = Pattern.compile("^[6-9]\\d{9}$");
        private static final Pattern EMAIL_PAT = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
        private static final Pattern PAN_PAT = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");
        private static final Pattern IFSC_PAT = Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

        public static boolean isValidMobile(String s) { return s != null && MOBILE_PAT.matcher(s).matches(); }
        public static boolean isValidEmail(String s) { return s != null && EMAIL_PAT.matcher(s).matches(); }
        public static boolean isValidPan(String s) { return s != null && PAN_PAT.matcher(s).matches(); }
        public static boolean isValidIfsc(String s) { return s != null && IFSC_PAT.matcher(s).matches(); }
    }

    public enum TransactionType { DEPOSIT, WITHDRAW, TRANSFER }
    public record Command(TransactionType type, String accountNumber, long amount) {}

    public static class CommandParser {
        public static Command parse(String line) {
            if (line == null || line.isBlank()) return null;
            String[] parts = line.trim().split("\\s+");
            if (parts.length != 3) return null;
            TransactionType type = TransactionType.valueOf(parts[0].toUpperCase());
            String acc = parts[1];
            long amt = Long.parseLong(parts[2]);
            return new Command(type, acc, amt);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Validator Tests ===");
        System.out.println("isValidMobile(\"9876543210\"): " + Validator.isValidMobile("9876543210"));
        System.out.println("isValidMobile(\"12345\"): " + Validator.isValidMobile("12345"));
        System.out.println("isValidEmail(\"henil@charusat.edu.in\"): " + Validator.isValidEmail("henil@charusat.edu.in"));
        System.out.println("isValidPan(\"ABCDE1234F\"): " + Validator.isValidPan("ABCDE1234F"));

        System.out.println("\n=== Command Parser Test ===");
        Command cmd = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println("Parsed Command: type=" + cmd.type() + ", accountNumber=" + cmd.accountNumber() + ", amount=" + cmd.amount());
    }
}
