package homework1;

public class Deque<T> {

    private Node head;
    private Node tail;
    private int size;

    private static class Node<T> {
        Node next;
        Node prev;
        T value;
    }

    public Deque() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public void pushLeft(T value) {
        Node node = new Node();
        node.value = value;
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;

        if (isEmpty()) {
            tail.prev = node;
        }

        size++;
    }

    public void pushRight(T value) {
        Node node = new Node();
        node.value = value;
        node.next = tail;
        node.prev = tail.prev;
        tail.prev = node;
        node.prev.next = node;

        if (isEmpty()) {
            head.next = node;
        }

        size++;
    }

    public T popLeft() {
        if (isEmpty()) {
            throw new RuntimeException("Deque is empty");
        }

        T value =(T) head.next.value;
        head.next.next.prev = head;
        head.next = head.next.next;
        size--;
        return value;
    }

    public T popRight() {
        if (isEmpty()) {
            throw new RuntimeException("Deque is empty");
        }

        T value =(T) tail.prev.value;
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
        size--;
        return value;
    }

    public int length() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        Node node = head.next;
        String result = "[";
        while (node != tail) {
            if (node.next != tail) {
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
