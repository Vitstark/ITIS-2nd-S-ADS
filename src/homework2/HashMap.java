package homework2;

import java.util.LinkedList;

public class HashMap<K, V> extends HashTable<K, V> {

    public HashMap(int numberOfCells, double maxFillingLevel) {
        super(numberOfCells, maxFillingLevel);
    }

    public HashMap(int numberOfCells) {
        super(numberOfCells);
    }

    public HashMap() {
        super();
    }

    public void grow() {
        numberOfCells *= 2;
        HashMap<K, V> newHashMap = new HashMap<>(numberOfCells, maxFillingLevel);

        for (LinkedList<Pair<K, V>> cell : notNullCells) {
            for (Pair<K, V> element : cell) {
                newHashMap.add(element);
            }
        }

        table = newHashMap.table;
        notNullCells = newHashMap.notNullCells;
    }

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

    public boolean add(K key, V value) {
        return add(new Pair<K, V>(key, value));
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

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("");
        for (LinkedList<Pair<K,V>> cell : table) {
            if (cell != null) {
                for (Pair<K,V> element : cell) {
                    string.append("{" + element.key + "=" + element.value + "}\n");
                }
            }
        }
        return string.toString();
    }

}
