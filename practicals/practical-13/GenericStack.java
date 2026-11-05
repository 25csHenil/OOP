import java.util.EmptyStackException;

class ArrayStack<T> {
    private Object[] elements;
    private int size = 0;

    public ArrayStack(int capacity) { elements = new Object[capacity]; }

    public void push(T item) {
        if (size == elements.length) throw new IllegalStateException("Stack overflow");
        elements[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (size == 0) throw new EmptyStackException();
        T item = (T) elements[--size];
        elements[size] = null;
        return item;
    }

    public boolean isEmpty() { return size == 0; }
}

public class GenericStack {
    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<>(5);
        stack.push("First");
        stack.push("Second");
        System.out.println("Popped: " + stack.pop());
        System.out.println("Popped: " + stack.pop());
    }
}
