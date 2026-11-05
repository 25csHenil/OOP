import java.util.Objects;

public class MiniBankP3 {
    public static class Customer implements Cloneable {
        private String name;
        private String email;
        private String mobile;
        private Address address;

        public static class Address {
            private final String line;
            private final String city;
            private final String pincode;

            public Address(String line, String city, String pincode) {
                this.line = line;
                this.city = city;
                this.pincode = pincode;
            }
            public String getLine() { return line; }
            public String getCity() { return city; }
            public String getPincode() { return pincode; }
            @Override
            public String toString() { return line + ", " + city + " - " + pincode; }
        }

        public Customer(String name, String email, String mobile, Address address) {
            this.name = name;
            this.email = email;
            this.mobile = mobile;
            this.address = address;
        }

        public Address getAddress() { return address; }
        public String getName() { return name; }

        @Override
        public Customer clone() {
            try {
                Customer copy = (Customer) super.clone();
                // Deep clone address if needed
                copy.address = new Address(this.address.line, this.address.city, this.address.pincode);
                return copy;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    public static class Account {
        private final String accountNumber;
        private final String ownerName;
        private final long balance;
        private final boolean active;

        public Account(String accountNumber, String ownerName, long balance) {
            this.accountNumber = accountNumber;
            this.ownerName = ownerName;
            this.balance = balance;
            this.active = true;
        }

        @Override
        public String toString() {
            return accountNumber + " | " + ownerName + " | " + balance + " (Active: " + active + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Account acc)) return false;
            return Objects.equals(this.accountNumber, acc.accountNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(accountNumber);
        }
    }

    public static void main(String[] args) {
        Account a1 = new Account("AC0001", "Riya", 4000);
        Account a2 = new Account("AC0001", "Riya Duplicate", 9999);

        System.out.println("Printing a1: " + a1);
        System.out.println("a1.equals(a2) [by AccountNumber]: " + a1.equals(a2));

        Customer.Address addr = new Customer.Address("101 Green Valley", "Ahmedabad", "380015");
        Customer c1 = new Customer("Henil", "henil@charusat.edu.in", "9876543210", addr);
        Customer c2 = c1.clone();
        System.out.println("Customer cloned: " + c2.getName() + " | Address: " + c2.getAddress());
        System.out.println("instanceof check: " + (a1 instanceof Account));
    }
}
