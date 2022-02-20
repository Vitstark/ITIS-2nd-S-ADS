package homework1;

public class ShiftReg<V> {

    private Node head;
    private int size;

    private static class Node<V> {
        Node next;
        Node prev;
        V value;
    }

    public ShiftReg() {
        head = new Node();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int length() {
        return size;
    }

    public void push(V value) {
        Node node = new Node();
        node.value = value;
        if (isEmpty()) {
            head.next = node;
            node.next = node;
            node.prev = node;
        } else {
            node.next = head.next;
            head.next = node;
            node.prev = node.next.prev;
            node.next.prev = node;
            node.prev.next = node;
        }

        size++;
    }

    public V pop() {
        if (isEmpty()) {
            throw new RuntimeException("ShiftReg is empty");
        }

        V value = (V) head.next.value;
        head.next.next.prev = head.next.prev;
        head.next.prev.next = head.next.next;
        head.next = head.next.next;
        size--;
        return value;
    }

    public void shiftLeft() {
        if (isEmpty()) {
            throw new RuntimeException("ShiftReg is Empty");
        }

        head.next = head.next.next;
    }

    public void shiftRight() {
        if (isEmpty()) {
            throw new RuntimeException("ShiftReg is Empty");
        }

        head.next = head.next.prev;
    }

    public void repr() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        Node node = head.next;
        String string = "[";
        for (int i = 0; i < length() - 1; i++) {
            string += node.value + ", ";
            node = node.next;
        }
        string += node.value + "]";
        return string;
    }




}
