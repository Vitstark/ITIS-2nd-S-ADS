import homework2.HashMap;
import homework2.HashTable;
import homework2.HashSet;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        HashSet<Integer> hashSet1 = new HashSet<>(16, 0.5);
        HashSet<Integer> hashSet2 = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            hashSet1.add(i);
            hashSet2.add(2*i);
        }


        System.out.println(hashSet1.difference(hashSet2));

    }
}
