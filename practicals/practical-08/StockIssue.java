class OutOfStockException extends Exception {
    private final int shortfall;
    public OutOfStockException(String item, int shortfall) {
        super("Out of stock for " + item + "! Short by: " + shortfall);
        this.shortfall = shortfall;
    }
    public int getShortfall() { return shortfall; }
}

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String msg) { super(msg); }
}

public class StockIssue {
    private int available = 10;

    public void issue(String item, int qty) throws OutOfStockException, InvalidQuantityException {
        if (qty <= 0) throw new InvalidQuantityException("Quantity must be positive: " + qty);
        if (qty > available) throw new OutOfStockException(item, qty - available);
        available -= qty;
        System.out.println("Issued " + qty + " units of " + item + ". Remaining: " + available);
    }

    public static void main(String[] args) {
        StockIssue warehouse = new StockIssue();
        int[] requests = {4, -2, 8};

        for (int req : requests) {
            try {
                System.out.println("Requesting " + req + " units:");
                warehouse.issue("Core i7 CPU", req);
            } catch (OutOfStockException | InvalidQuantityException e) {
                System.out.println("Handled safely: " + e.getMessage());
            }
        }
    }
}
