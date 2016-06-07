public interface PriorityQueue<Event>{

    boolean isEmpty();
    void add(K key, V value);
    Entry<K,V> removeMin();
    Entry<K,V> peekMin();
}
