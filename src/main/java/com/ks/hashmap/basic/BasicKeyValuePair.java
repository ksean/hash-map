package com.ks.hashmap.basic;

import com.ks.hashmap.KeyValuePair;

public class BasicKeyValuePair<K, V> implements KeyValuePair<K, V> {
    private K key;
    private V value;

    public BasicKeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }
}
