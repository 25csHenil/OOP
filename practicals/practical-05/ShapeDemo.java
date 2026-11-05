abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    private final double radius;
    Circle(double radius) { this.radius = radius; }
    @Override double area() { return Math.PI * radius * radius; }
}

class Rectangle extends Shape {
    private final double width, height;
    Rectangle(double width, double height) { this.width = width; this.height = height; }
    @Override double area() { return width * height; }
}

class Triangle extends Shape {
    private final double base, height;
    Triangle(double base, double height) { this.base = base; this.height = height; }
    @Override double area() { return 0.5 * base * height; }
}

public class ShapeDemo {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle(5.0),
            new Rectangle(4.0, 6.0),
            new Triangle(3.0, 8.0)
        };

        double totalArea = 0;
        double maxArea = 0;
        Shape largest = null;

        for (Shape s : shapes) {
            double a = s.area();
            totalArea += a;
            if (a > maxArea) {
                maxArea = a;
                largest = s;
            }
            System.out.printf("Shape: %-10s | Area: %.2f\n", s.getClass().getSimpleName(), a);
        }
        System.out.printf("Total Area: %.2f | Largest: %s (%.2f)\n", totalArea, largest.getClass().getSimpleName(), maxArea);
    }
}
