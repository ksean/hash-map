package com.ks.hashmap.basic;

import com.ks.hashmap.KeyValuePair;

public class BasicKeyValuePair<K, V> implements KeyValuePair<K, V> {
    private K key;
    private V value;
    private KeyValuePair<K, V> next;
    private String hash;

    public BasicKeyValuePair(K key, V value, KeyValuePair<K, V> next, String hash) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public KeyValuePair<K, V> getNext() {
        return next;
    }

    @Override
    public String getHash() {
        return hash;
    }
}
