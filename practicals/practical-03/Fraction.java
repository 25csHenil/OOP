import java.util.Objects;

public class Fraction {
    private final int num;
    private final int den;

    private static int gcd(int a, int b) {
        return b == 0 ? Math.abs(a) : gcd(b, a % b);
    }

    public Fraction(int num, int den) {
        if (den == 0) throw new IllegalArgumentException("Denominator cannot be zero");
        int g = gcd(num, den);
        int sign = (den < 0) ? -1 : 1;
        this.num = sign * (num / g);
        this.den = sign * (den / g);
    }

    @Override
    public String toString() {
        return num + "/" + den;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fraction f)) return false;
        return this.num == f.num && this.den == f.den;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, den);
    }

    public static void main(String[] args) {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 4);
        Fraction f3 = new Fraction(3, 6);
        System.out.println("new Fraction(2,4) prints: " + f2);
        System.out.println("f1.equals(f2): " + f1.equals(f2));
        System.out.println("f2.equals(f3): " + f2.equals(f3));
    }
}
