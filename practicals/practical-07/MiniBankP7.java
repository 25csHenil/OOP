import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MiniBankP7 {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Id {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Positive {
        String message() default "must be > 0";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface MaxLength {
        int value();
    }

    public static class Account {
        @Id
        private final String accountNumber;
        private String ownerName;

        @Positive
        private long balance;

        public Account(String accountNumber, String ownerName, long balance) {
            this.accountNumber = accountNumber;
            this.ownerName = ownerName;
            this.balance = balance;
        }
    }

    public static class AnnotationValidator {
        public static String[] validate(Object obj) {
            List<String> errors = new ArrayList<>();
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    Object val = f.get(obj);
                    if (f.isAnnotationPresent(Positive.class)) {
                        Positive p = f.getAnnotation(Positive.class);
                        if (val instanceof Number num && num.longValue() <= 0) {
                            errors.add(f.getName() + " " + p.message());
                        }
                    }
                } catch (IllegalAccessException e) {
                    errors.add("Access error on: " + f.getName());
                }
            }
            return errors.toArray(new String[0]);
        }
    }

    public static void main(String[] args) {
        Account invalidAcc = new Account("AC0009", "Invalid User", -100);
        System.out.println("Validating account with negative balance:");
        String[] errors = AnnotationValidator.validate(invalidAcc);
        for (String err : errors) {
            System.out.println("  Validation Error: " + err);
        }
    }
}
