package homework1;

public class LinkedArray<V> {

    private ArrayNode head;

    private ArrayNode lastNode;

    private int indexOfLastElement;

    private final int BASE;

    private int size;

    private int countOfNodes;

    /////////////////////////////////////////////////////////////////
    // ArrayNode

    private static class ArrayNode<V> {
        private V[] array;
        private ArrayNode next;
        private ArrayNode prev;
        private int capacity;

        private ArrayNode(int base) {
            capacity = 1 << base;
            array = (V[]) new Object[capacity];
        }

        private ArrayNode() {
            capacity = 0;
        }

    }

    /////////////////////////////////////////////////////////////////
    // Constructors

    public LinkedArray(int BASE) {
        this.BASE = BASE;
        head = new ArrayNode();
        lastNode = head;
    }

    public LinkedArray() {
        this(3);
    }

    /////////////////////////////////////////////////////////////////
    // Methods


    public void append(V value) {

        indexOfLastElement++;

        if (lastNode.capacity <= indexOfLastElement) {
            ArrayNode<V> newNode = new ArrayNode(BASE + countOfNodes);
            lastNode.next = newNode;
            lastNode = newNode;
            countOfNodes++;
            indexOfLastElement = 0;
        }

        lastNode.array[indexOfLastElement] = value;

        size++;
    }

    public void delete() {

        lastNode.array[indexOfLastElement] = null;

        if (indexOfLastElement == 0) {
            ArrayNode node = head;
            while(node.next.next != null) {
                node = node.next;
            }
            node.next = null;
            lastNode = node;
            countOfNodes--;
            indexOfLastElement = lastNode.capacity;
        }

        indexOfLastElement--;

        size--;
    }

    public int length() {
        return size;
    }

    public void repr() {
        System.out.println(toString());
    }

    public V get(int numberOfElement) {
        ArrayNode arrayNode = head.next;

        while (arrayNode != null && numberOfElement >= arrayNode.capacity) {
            numberOfElement -= arrayNode.capacity;
            arrayNode = arrayNode.next;
        }
        try {
            return (V) arrayNode.array[numberOfElement];
        } catch (NullPointerException e) {
            throw new IndexOutOfBoundsException("Element does not exist");
        }
    }

    public String toString() {

        String string = "[";

        for (ArrayNode node = head; node != null ; node = node.next) {

            for (int i = 0; i < node.capacity; i++) {

                if (i + 1 == node.capacity) {
                    string += node.array[i];
                } else {
                    string += node.array[i] + ", ";
                }

            }

            if (node.next != null) {
                string += "]\n[";
            }

        }

        return string + "]";
    }

}
