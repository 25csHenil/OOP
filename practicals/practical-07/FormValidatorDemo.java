import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
    String message() default "Field cannot be empty";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
    String message() default "Value exceeds maximum allowed length";
}

class SignupForm {
    @NotBlank(message = "Username is mandatory")
    @MaxLength(value = 8, message = "Username cannot exceed 8 chars")
    private String username;

    @NotBlank(message = "Email is mandatory")
    private String email;

    public SignupForm(String username, String email) {
        this.username = username;
        this.email = email;
    }
}

public class FormValidatorDemo {
    public static List<String> validate(Object obj) throws Exception {
        List<String> errors = new ArrayList<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object val = field.get(obj);

            if (field.isAnnotationPresent(NotBlank.class)) {
                NotBlank ann = field.getAnnotation(NotBlank.class);
                if (val == null || val.toString().trim().isEmpty()) {
                    errors.add(field.getName() + ": " + ann.message());
                }
            }
            if (field.isAnnotationPresent(MaxLength.class)) {
                MaxLength ann = field.getAnnotation(MaxLength.class);
                if (val != null && val.toString().length() > ann.value()) {
                    errors.add(field.getName() + ": " + ann.message() + " (Max: " + ann.value() + ")");
                }
            }
        }
        return errors;
    }

    public static void main(String[] args) throws Exception {
        SignupForm invalidForm = new SignupForm("SuperLongAdminUser", "");
        List<String> errors = validate(invalidForm);
        System.out.println("Form Validation Results (Errors detected):");
        errors.forEach(e -> System.out.println("  * " + e));
    }
}
