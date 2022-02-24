package homework1;

public class PriorQueue<T> {

    private PriorNode head;
    private PriorNode tail;
    private int size;

    public PriorQueue() {
        head = new PriorNode();
        tail = head;
    }

    public void enqueue(T value, int priority) {

        PriorNode node = head;
        PriorNode<T> newNode = new PriorNode<>(value, priority);

        if (isEmpty()) {
            head.next = newNode;
            size++;
            tail = newNode;
            return;
        }

        while (node.next != null && priority > node.next.priority) {
            node = node.next;
        }

        newNode.next = node.next;
        node.next = newNode;

        if (newNode.next == null) {
            tail = newNode;
        }

        size++;
    }

    public void enqueue(T value) {
        enqueue(value, 0);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }

        T result = (T) tail.value;
        PriorNode node = head;
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

    @Override
    public String toString() {
        PriorNode node = head.next;
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

    private static class PriorNode<T> {
        private int priority;
        private T value;
        private PriorNode next;

        PriorNode() {
        }

        PriorNode(T value) {
            this.value = value;
        }

        PriorNode(T value, int priority) {
            this.value = value;
            this.priority = priority;
        }

    }
}
