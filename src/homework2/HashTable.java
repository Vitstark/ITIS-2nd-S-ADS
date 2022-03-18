package homework2;

import java.util.LinkedList;

public abstract class HashTable<K,V> {

    protected static class Pair<K,V> {
        protected K key;
        protected V value;
        private int hashCode;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
            this.hashCode = key.hashCode();
        }

        public Pair(K key) {
            this(key,(V) key);
        }

        public K getKey() { return key; }

        public V getValue() { return value; }

        @Override
        public int hashCode() { return hashCode; }

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true;}
            if (o == null || getClass() != o.getClass()) { return false;}

            Pair<K, V> pair = (Pair<K, V>) o;

            if (pair.value == null ^ this.value == null) { return false; }
            return value.equals(pair.value);
        }
    }

    protected int size;

    protected int numberOfCells;

    protected double maxFillingLevel;

    protected LinkedList<Pair<K,V>>[] table;

    protected LinkedList<LinkedList<Pair<K,V>>> notNullCells;

    public HashTable(int numberOfCells, double maxFillingLevel) {
        table = new LinkedList[numberOfCells];
        notNullCells = new LinkedList<>();
        this.numberOfCells = numberOfCells;
        this.maxFillingLevel = maxFillingLevel;
    }

    public HashTable(int numberOfCells) {
        this(numberOfCells, 0.5);
    }

    public HashTable() {
        this(16, 0.5);
    }

    public int getIndex(Pair<K,V> element) {
        int index = element.hashCode() % numberOfCells;
        return index > 0? index : -index;
    }

    public int getIndex(K key) {
        int index = key.hashCode() % numberOfCells;
        return index > 0? index : -index;
    }
    public int size() { return size; }

    public int getNumberOfCells() { return numberOfCells; }

    public LinkedList<LinkedList<Pair<K, V>>> getNotNullCells() { return notNullCells; }

    public boolean add(Pair<K,V> newElement) {
        if ((notNullCells.size() / (1.0 * numberOfCells)) >= maxFillingLevel) {
            grow();
        }

        int index = getIndex(newElement);
        LinkedList<Pair<K,V>> cell = table[index];

        if (cell == null) {
            table[index] = new LinkedList<>();
            notNullCells.add(table[index]);
            cell = table[index];
        }

        for (Pair<K,V> element : cell) {
            if (newElement.hashCode() == element.hashCode()) {
                if (newElement.equals(element)) { return false;}
            }
        }

        for (int i = 0; i < cell.size(); i++) {
            Pair<K,V> element = cell.get(i);
            if (element.key == newElement.key) {
                table[index].get(i).value = newElement.value;
                return true;
            }
        }

        size++;

        return cell.add(newElement);
    }

    public boolean remove(K key) {

        int index = getIndex(key);
        LinkedList<Pair<K,V>> cell = table[index];

        if (cell == null) { return false; }

        Pair<K,V> pair = null;
        for (Pair<K,V> element : cell) {
            if (element.key == key) {
                pair = element;
                break;
            }
        }

        if (pair != null) {
            cell.remove(pair);
            size--;
            if (cell.size() == 0) {
                notNullCells.remove(cell);
                table[index] = null;
            }
            return true;
        }

        return false;
    }

    public boolean contains(K key) {

        int index = getIndex(key);
        LinkedList<Pair<K,V>> cell = table[index];

        if (cell == null) { return false;}

        for (Pair<K,V> element : cell) {
            if (element.key == key) {
                return true;
            }
        }

        return false;
    }

    public abstract void grow();
}
