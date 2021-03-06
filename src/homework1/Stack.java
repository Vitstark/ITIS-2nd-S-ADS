package homework1;

public class Stack<T> {

    private Node head;
    private int size;

    public Stack() {
        head = new Node();
    }

    public void push(T value) {
        Node node = new Node();
        node.value = value;
        node.next = head.next;
        head.next = node;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        T result = (T) head.next.value;
        head.next = head.next.next;
        size--;
        return result;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        return (T) head.next.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int length() {
        return size;
    }

    private static class Node<T> {
        T value;
        Node next;
    }

    @Override
    public String toString() {
        Node node = head.next;
        String result = "[";
        while (node != null) {
            if (node.next != null) {
                result += node.value + ", ";
            } else {
                result += node.value;
            }
            node = node.next;
        }
        result += "]";
        return result;
    }

}
