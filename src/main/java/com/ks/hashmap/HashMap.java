package com.ks.hashmap;

public interface HashMap<K, V> {
    public int size();
    public int buckets();
    public double loadFactor();
    public void add(KeyValuePair<K, V> keyValuePair);
    public V get(K key);
}
