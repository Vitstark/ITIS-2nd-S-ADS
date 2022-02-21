package homework1;

public class LinkedArray<V> {

    private ArrayNode head;

    private final int BASE;

    private int size;

    private int countOfArrays;

    /////////////////////////////////////////////////////////////////
    // ArrayNode

    private static class ArrayNode<V> {
        private V[] array;
        private ArrayNode next;
        private int capacity;

        private ArrayNode(int base) {
            capacity = 1 << base;
            array = (V[]) new Object[capacity];
        }

        private ArrayNode() {
        }

    }

    /////////////////////////////////////////////////////////////////
    // Constructors

    public LinkedArray(int BASE) {
        this.BASE = BASE;
        head = new ArrayNode();
    }

    public LinkedArray() {
        this(3);
    }

    /////////////////////////////////////////////////////////////////
    // Methods

    public int length() {
        return size;
    }

    private void grow() {
        ArrayNode arrayNode = head;

        while (arrayNode.next != null) {
            arrayNode = arrayNode.next;
        }

        ArrayNode newArrayNode = new ArrayNode(BASE + countOfArrays);
        countOfArrays++;
        arrayNode.next = newArrayNode;
    }

    private void decline() {
        ArrayNode arrayNode = head;

        if (arrayNode.next != null) {
            while (arrayNode.next.next != null) {
                arrayNode = arrayNode.next;
            }
        }

        arrayNode.next = null;
        countOfArrays--;
    }


    public void append(V value) {
        ArrayNode arrayNode = head;
        int number = size;

        if (arrayNode.next == null) {
            grow();
        }

        while (arrayNode.next != null) {
            number -= arrayNode.capacity;
            arrayNode = arrayNode.next;
        }

        if (number == arrayNode.capacity) {
            grow();
            arrayNode = arrayNode.next;
            number = 0;
        }

        arrayNode.array[number] = value;
        size++;
    }

    public void delete() {

        if (size == 0) {
            throw new IndexOutOfBoundsException("LinkedArray is empty");
        }

        ArrayNode arrayNode = head.next;
        int number = size - 1;

        while (arrayNode.next != null) {
            number -= arrayNode.capacity;
            arrayNode = arrayNode.next;
        }

        arrayNode.array[number] = null;

        if (arrayNode.array[0] == null) {
            decline();
        }

        size--;
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

    public void repr() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        ArrayNode arrayNode = head.next;
        String string = "[";
        for (int i = 0; i < countOfArrays; i++) {
            for (int j = 0; j < arrayNode.capacity - 1; j++) {
                string += arrayNode.array[j] + ", ";
            }
            string += arrayNode.array[arrayNode.capacity - 1] + "]\n[";
            arrayNode = arrayNode.next;
        }
        return string.substring(0, string.length() - 1);
    }
}
