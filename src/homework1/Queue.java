package homework1;

public class Queue<T> {

    private Node head;
    private Node tail;
    private int size;

    public Queue() {
        head = new Node();
        tail = head;
    }

    public void enqueue(T value) {
        Node node = new Node();

        if (isEmpty()) {
            tail = node;
        }

        node.value = value;
        node.next = head.next;
        head.next = node;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }

        T result = (T) tail.value;
        Node node = head;
        while (node.next.next != null) {
            node = node.next;
        }
        tail = node;
        node.next = null;
        size--;
        return result;
    }

    public T check() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }

        return (T) tail.value;
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
