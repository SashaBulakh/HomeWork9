
import java.util.Arrays;
import java.util.Iterator;

import static java.util.Objects.requireNonNull;

interface Map<K, V>{
    V put(K key, V value);
    V get(K key);
    int size();
    V remove(K key);
    void clear();
}


public class MyHashMap<K, V> implements Map<K, V> {

    public static void main(String[] args) {
        Map<String, String> map = new MyHashMap<>();

        map.put("First", "1");
        map.put("Second", "2");
        map.put("Third", "3");
        map.put("Fours", "4");
        map.put("Fifth", "5");
        map.size();
        map.remove("First");
        map.get("Third");
        map.clear();




    }
    private static final int DEFAULT_CAPACITY = 10;
    private static final float RESIZE_THRESHOLD = 1.0f;
    private Node<K, V>[] table;
    private int size;

    public static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap(int initialCapacity) {
        verifyCapacity(initialCapacity);
        this.table = new Node[initialCapacity];
    }

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public static int calculateIndex(Object key, int tableCapacity) {
        var hash = key.hashCode() ^ (key.hashCode() >> 16);
        return hash & (tableCapacity - 1);
    }

    private void resizeIfNeeded() {
        if (size / (float) table.length > RESIZE_THRESHOLD) {
            resizeTable(2 * table.length);
        }
    }

    private V putOnTable(Node<K, V>[] table, K key, V value) {
        var newNode = new Node<>(requireNonNull(key), requireNonNull(value));
        var index = calculateIndex(key, table.length);
        if (table[index] == null) { // add new head key
            table[index] = newNode;
        } else {
            var current = table[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    var prevValue = current.value;
                    current.value = value;
                    return prevValue;
                }
                current = current.next;
            }
            if (current.key.equals(key)) {
                var prevValue = current.value;
                current.value = value;
                return prevValue;
            }
            current.next = newNode; // attach new key to the end of the list
        }
        size++;
        return null;
    }

    public void resizeTable(int newCapacity) {
        verifyCapacity(newCapacity);
        @SuppressWarnings("unchecked") Node<K, V>[] newTable = new Node[newCapacity];
        for (var head : table) {
            var current = head;
            while (current != null) {
                putOnTable(newTable, current.key, current.value);
                current = current.next;
            }
        }
        table = newTable;
    }

    private void verifyCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity (table array size) must be positive");
        }
    }

    @Override
    public V put(K key, V value) {
        resizeIfNeeded();
        return putOnTable(table, key, value);
    }

    @Override
    public V get(K key) {
        var index = calculateIndex(requireNonNull(key), table.length);
        var current = table[index];
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V remove(K key) {
        var index = calculateIndex(requireNonNull(key), table.length);
        var current = table[index];
        if (current != null) {
            if (current.key == key) {
                var value = current.value;
                table[index] = current.next;
                size--;
                return value;
            }
            while (current.next != null) {
                if (current.next.key == key) {
                    var value = current.value;
                    table[index] = current.next;
                    size--;
                    return value;
                }
                current = current.next;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        table = null;
        size = 0;
    }

}

