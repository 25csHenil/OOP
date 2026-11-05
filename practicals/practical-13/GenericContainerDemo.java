class Box<T> {
    private T item;
    public void set(T item) { this.item = item; }
    public T get() { return item; }
}

class Pair<A, B> {
    private final A first;
    private final B second;
    public Pair(A first, B second) { this.first = first; this.second = second; }
    public A getFirst() { return first; }
    public B getSecond() { return second; }
}

public class GenericContainerDemo {
    public static <T> T getFirstElement(T[] array) {
        if (array == null || array.length == 0) return null;
        return array[0];
    }

    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.set("MiniBank Security Token");
        System.out.println("Box contains: " + stringBox.get());

        Pair<String, Integer> pair = new Pair<>("HTTP_OK", 200);
        System.out.println("Pair: " + pair.getFirst() + " -> " + pair.getSecond());

        String[] names = {"Henil", "Riya", "Aarav"};
        System.out.println("First name in array: " + getFirstElement(names));
    }
}
