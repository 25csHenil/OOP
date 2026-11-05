abstract class Employee {
    private final String id;
    private final String name;

    Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    abstract double monthlySalary();
}

class FullTime extends Employee {
    private final double salary;
    FullTime(String id, String name, double salary) { super(id, name); this.salary = salary; }
    @Override double monthlySalary() { return salary; }
}

class PartTime extends Employee {
    private final double hours, rate;
    PartTime(String id, String name, double hours, double rate) { super(id, name); this.hours = hours; this.rate = rate; }
    @Override double monthlySalary() { return hours * rate; }
}

class Intern extends Employee {
    private final double stipend;
    Intern(String id, String name, double stipend) { super(id, name); this.stipend = stipend; }
    @Override double monthlySalary() { return stipend; }
}

public class PayrollDemo {
    public static void main(String[] args) {
        Employee[] employees = {
            new FullTime("E101", "Alice", 65000),
            new PartTime("E102", "Bob", 80, 450),
            new Intern("E103", "Henil", 25000)
        };

        double total = 0;
        for (Employee e : employees) {
            double sal = e.monthlySalary();
            total += sal;
            String note = (e instanceof Intern) ? " [INTERN - Subject to academic review]" : "";
            System.out.printf("%s (%s): Rs. %.2f%s\n", e.getName(), e.getId(), sal, note);
        }
        System.out.printf("Total Monthly Payroll: Rs. %.2f\n", total);
    }
}
