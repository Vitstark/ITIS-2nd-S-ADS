package homework1;

public class Stack<T> {

    private Node head;
    private int length;

    public Stack() {
        head = new Node();
    }

    public void push(T value) {
        Node node = new Node();
        node.value = value;
        node.next = head.next;
        head.next = node;
        length++;
    }

    public T pop() {
        T result = (T) head.next.value;
        head.next = head.next.next;
        length--;
        return result;
    }

    public T peek() {
        return (T) head.next.value;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    private static class Node<T> {
        T value;
        Node next;
    }

    @Override
    public String toString() {
        Node node = head.next;
        String result = "{";
        while (node != null) {
            if (node.next != null) {
                result += node.value + ", ";
            } else {
                result += node.value;
            }
            node = node.next;
        }
        result += "}";
        return result;
    }

}
