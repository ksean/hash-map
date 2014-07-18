package com.ks.hashmap.basic;

import com.ks.hashmap.HashFunction;
import com.ks.hashmap.HashMap;
import com.ks.hashmap.KeyValuePair;

import java.util.ArrayList;
import java.util.List;

public class BasicHashMap<K, V> implements HashMap<K, V> {
    private int size = 0;
    private int buckets = 0;
    private List<KeyValuePair<K, V>> digest;
    private HashFunction hashFunction = new BasicHashFunction();

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public int buckets() {
        return buckets;
    }

    @Override
    public float loadFactor() {
        return (buckets == 0 ? 0 : size/buckets);
    }

    @Override
    public List<KeyValuePair<K, V>> digest() {
        return digest;
    }

    @Override
    public HashFunction hashFunction() {
        return hashFunction;
    }

    @Override
    public void put(K key, V value) {
        size++;

        BasicKeyValuePair<K, V> basicKeyValuePair = new BasicKeyValuePair<K, V>(key, value, null, hashFunction.hash(key));

        digest = new ArrayList<KeyValuePair<K, V>>(1);
        digest.add(basicKeyValuePair);
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean hasKey(K key) {
        if (size == 0) {
            return false;
        }

        for (KeyValuePair keyValuePair : digest) {
            if (keyValuePair.getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hasValue(V value) {
        if (size == 0) {
            return false;
        }

        for (KeyValuePair keyValuePair : digest) {
            if (keyValuePair.getValue().equals(value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void remove(K key) {
        size--;
    }
}
