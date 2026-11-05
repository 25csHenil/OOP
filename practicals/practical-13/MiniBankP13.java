import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.*;

public class MiniBankP13 {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Id {}

    public static class Repository<T> {
        private final Map<String, T> storage = new HashMap<>();

        public T save(T entity) {
            try {
                String id = extractId(entity);
                storage.put(id, entity);
                return entity;
            } catch (Exception e) {
                throw new RuntimeException("Failed to save entity: " + e.getMessage(), e);
            }
        }

        public T findById(String id) { return storage.get(id); }
        public List<T> findAll() { return new ArrayList<>(storage.values()); }
        public boolean delete(String id) { return storage.remove(id) != null; }

        @SafeVarargs
        public final void saveAll(T... items) {
            for (T item : items) save(item);
        }

        private String extractId(T entity) throws IllegalAccessException {
            for (Field f : entity.getClass().getDeclaredFields()) {
                if (f.isAnnotationPresent(Id.class)) {
                    f.setAccessible(true);
                    Object val = f.get(entity);
                    return (val != null) ? val.toString() : null;
                }
            }
            throw new IllegalArgumentException("Entity missing @Id annotated field: " + entity.getClass().getSimpleName());
        }
    }

    public static class Account {
        @Id private final String accountNumber;
        private final String ownerName;
        private final long balance;

        public Account(String accNo, String owner, long balance) {
            this.accountNumber = accNo;
            this.ownerName = owner;
            this.balance = balance;
        }
        @Override public String toString() {
            return accountNumber + " | " + ownerName + " | Rs. " + balance;
        }
    }

    public static class Customer {
        @Id private final String customerId;
        private final String name;

        public Customer(String id, String name) {
            this.customerId = id;
            this.name = name;
        }
        @Override public String toString() {
            return customerId + " | " + name;
        }
    }

    public static void main(String[] args) {
        Repository<Account> accRepo = new Repository<>();
        Account a1 = new Account("AC0001", "Riya", 5000);
        Account a2 = new Account("AC0002", "Henil", 12000);
        Account a3 = new Account("AC0003", "Aarav", 8000);

        accRepo.saveAll(a1, a2, a3);
        System.out.println("Fetched from Repository by ID AC0002: " + accRepo.findById("AC0002"));

        System.out.println("\nAll accounts in Repository:");
        accRepo.findAll().forEach(a -> System.out.println("  " + a));

        // Reuse for Customer without code change!
        Repository<Customer> custRepo = new Repository<>();
        custRepo.save(new Customer("CUST101", "Henil Trivedi"));
        System.out.println("\nReused Repository for Customer: " + custRepo.findById("CUST101"));
    }
}
