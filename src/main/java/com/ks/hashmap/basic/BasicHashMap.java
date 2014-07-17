package com.ks.hashmap.basic;

import com.ks.hashmap.HashMap;
import com.ks.hashmap.KeyValuePair;

public class BasicHashMap<K, V> implements HashMap<K, V> {
    private int size = 0;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int buckets() {
        return 0;
    }

    @Override
    public double loadFactor() {
        return 0;
    }

    @Override
    public void add(KeyValuePair<K, V> keyValuePair) {
        this.size++;
    }

    @Override
    public V get(K key) {
        return null;
    }
}
