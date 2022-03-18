package homework2;

import java.util.Collection;
import java.util.LinkedList;

public class HashSet<K> extends HashTable<K,K>{

    public HashSet(int numberOfCells, double maxFillingLevel) {
        super(numberOfCells, maxFillingLevel);
    }

    public HashSet(int numberOfCells) {
        super(numberOfCells);
    }

    public HashSet() {
        super();
    }

    public HashSet(Collection<K> collection) {
        this(2 * collection.size(), 0.5);
        for (K element : collection) {
            add(new Pair<K,K>(element));
        }
    }

    public void grow() {
        numberOfCells *= 2;
        HashMap<K, K> newHashMap = new HashMap<>(numberOfCells, maxFillingLevel);

        for (LinkedList<Pair<K, K>> cell : notNullCells) {
            for (Pair<K, K> element : cell) {
                newHashMap.add(element);
            }
        }

        table = newHashMap.table;
        notNullCells = newHashMap.notNullCells;
    }

    public boolean add(K key) {
        return add(new Pair<K,K>(key));
    }

    private boolean contains(Pair<K,K> pair) {
        int index = getIndex(pair);
        LinkedList<Pair<K,K>> cell = table[index];

        if (cell == null) {
            return false;
        }

        for (Pair<K,K> element : cell) {
            if (element.hashCode() == pair.hashCode()) {
                return element.equals(pair);
            }
        }

        return false;
    }

    public HashSet<K> intersection(HashSet<K> hashSet) {
        if (this.size() > hashSet.size()) {
            return intersection(hashSet, this);
        } else {
            return intersection(this, hashSet);
        }
    }

    private HashSet<K> intersection(HashSet<K> minHashSet, HashSet<K> maxHashSet) {
        HashSet<K> unionOfHashSets = new HashSet<>(2 * minHashSet.size());
        for (LinkedList<Pair<K,K>> cell : minHashSet.notNullCells) {
            for (Pair<K,K> element : cell) {
                if (maxHashSet.contains(element)) {
                    unionOfHashSets.add(element);
                }
            }
        }
        return unionOfHashSets;
    }

    public HashSet<K> union(HashSet<K> hashSet) {
        HashSet<K> newHashSet = new HashSet<>(this.size() + hashSet.size());
        for (LinkedList<Pair<K,K>> cell : this.notNullCells) {
            for (Pair<K,K> element : cell) {
                newHashSet.add(element);
            }
        }
        for (LinkedList<Pair<K,K>> cell : hashSet.notNullCells) {
            for (Pair<K,K> element : cell) {
                newHashSet.add(element);
            }
        }
        return newHashSet;
    }

    public HashSet<K> difference(HashSet<K> hashSet) {
        if (this.size() > hashSet.size()) {
            return difference(hashSet, this);
        } else {
            return difference(this, hashSet);
        }
    }

    private HashSet<K> difference(HashSet<K> minHashSet, HashSet<K> maxHashSet) {
        HashSet<K> unionOfHashSets = new HashSet<>(2 * minHashSet.size());
        for (LinkedList<Pair<K,K>> cell : minHashSet.notNullCells) {
            for (Pair<K,K> element : cell) {
                if (!maxHashSet.contains(element)) {
                    unionOfHashSets.add(element);
                }
            }
        }
        return unionOfHashSets;
    }

    public String toString() {
        StringBuilder string = new StringBuilder("[");
        for (LinkedList<Pair<K,K>> cell : table) {
            if (cell != null) {
                for (Pair<K,K> element : cell) {
                    string.append(element.key + ", ");
                }
            }
        }
        if (string.length() > 2) {string.delete(string.length() - 2, string.length()); }
        string.append("] ");
        return string.toString();
    }

}
