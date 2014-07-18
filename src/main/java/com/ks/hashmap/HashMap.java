package com.ks.hashmap;

import java.util.List;

public interface HashMap<K, V> {
    public int size();
    public boolean empty();
    public int buckets();
    public float loadFactor();
    public List<KeyValuePair<K, V>> digest();
    public HashFunction hashFunction();

    public void put(K key, V value);
    public void remove(K key);
    public V get(K key);
    public boolean hasKey(K key);
    public boolean hasValue(V value);
}
