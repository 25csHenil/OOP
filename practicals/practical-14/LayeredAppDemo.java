import java.util.ArrayList;
import java.util.List;

class ItemModel {
    private final String name;
    private final double price;
    public ItemModel(String name, double price) { this.name = name; this.price = price; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}

class ItemService {
    private final List<ItemModel> items = new ArrayList<>();
    public void addItem(String name, double price) {
        if (price <= 0) throw new IllegalArgumentException("Price must be positive");
        items.add(new ItemModel(name, price));
    }
    public int getCount() { return items.size(); }
}

public class LayeredAppDemo {
    public static void main(String[] args) {
        ItemService service = new ItemService();
        System.out.println("=== Testing Layered Application ===");
        service.addItem("Keyboard", 1500);
        service.addItem("Mouse", 800);
        System.out.println("Items added successfully. Total count: " + service.getCount());

        try {
            service.addItem("Invalid", -50);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected invalid price exception: " + e.getMessage());
        }
    }
}
