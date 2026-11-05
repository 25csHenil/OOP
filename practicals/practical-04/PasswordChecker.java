public class PasswordChecker {
    public static String strength(String pw) {
        if (pw == null) return "Weak";
        int score = 0;
        if (pw.length() >= 8) score++;
        if (pw.matches(".*[A-Z].*")) score++;
        if (pw.matches(".*[0-9].*")) score++;
        if (pw.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?].*")) score++;

        return switch (score) {
            case 4 -> "Strong";
            case 2, 3 -> "Medium";
            default -> "Weak";
        };
    }

    public static void main(String[] args) {
        String[] test = {"abc", "password123", "Abcd1234!"};
        for (String p : test) {
            System.out.println(p + " -> " + strength(p));
        }
    }
}
