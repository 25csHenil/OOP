import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {}

class CalculatorTestSuite {
    @Run
    public void testAddition() {
        assert (2 + 3) == 5;
        System.out.println("  [PASS] testAddition");
    }

    public void helperMethod() {
        // Not annotated, will not run
    }

    @Run
    public void testMultiplication() {
        assert (4 * 5) == 20;
        System.out.println("  [PASS] testMultiplication");
    }
}

public class MiniTestRunner {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = CalculatorTestSuite.class;
        Object instance = clazz.getDeclaredConstructor().newInstance();
        int count = 0;
        System.out.println("Executing @Run annotated test methods via Reflection:");
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Run.class)) {
                m.invoke(instance);
                count++;
            }
        }
        System.out.println("MiniTestRunner summary: " + count + " tests passed successfully.");
    }
}
