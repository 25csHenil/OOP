package com.minibank.model;
import com.minibank.model.annotation.Id;
import java.io.Serializable;

public class Customer implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private final String customerId;
    private String name;
    private String email;
    private String mobile;
    private Address address;

    public static class Address implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String line, city, pincode;
        public Address(String line, String city, String pincode) {
            this.line = line; this.city = city; this.pincode = pincode;
        }
        @Override public String toString() { return line + ", " + city + " - " + pincode; }
    }

    public Customer(String customerId, String name, String email, String mobile, Address address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public Address getAddress() { return address; }

    @Override
    public Customer clone() {
        try {
            Customer copy = (Customer) super.clone();
            if (this.address != null) {
                copy.address = new Address(this.address.line, this.address.city, this.address.pincode);
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
