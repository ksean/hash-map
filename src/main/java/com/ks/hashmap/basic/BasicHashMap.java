package com.ks.hashmap.basic;

import com.ks.hashmap.HashFunction;
import com.ks.hashmap.HashMap;
import com.ks.hashmap.KeyValuePair;

import java.lang.reflect.Array;

public class BasicHashMap<K, V> implements HashMap<K, V> {
    private int size = 0;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private HashFunction hashFunction;
    private static final KeyValuePair<?, ?>[] EMPTY_TABLE = {};
    private KeyValuePair<K, V>[] table;

    public BasicHashMap(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
        this.table = (KeyValuePair<K, V>[]) EMPTY_TABLE;
    }

    @Override
    public HashFunction hashFunction() {
        return hashFunction;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public float loadFactor() {
        return (table.length == 0 ? 0 : size/table.length);
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public KeyValuePair<K, V>[] table() {
        return table;
    }

    @Override
    public void put(final K key, final V value) {
        if (!hasKey(key)) {
            size++;
            resize();
        }

        BasicKeyValuePair<K, V> basicKeyValuePair = new BasicKeyValuePair<K, V>(key, value, null);

        int keyIndex = keyIndexFromKey(key);

        if (hasKey(key)) {
            table[keyIndex] = basicKeyValuePair;

        } else if (table[keyIndex] == null) {
            table[keyIndex] = basicKeyValuePair;

        } else {
            KeyValuePair<K, V> currentKeyValuePair = table[keyIndex];

            while (currentKeyValuePair.getNext() != null) {
                currentKeyValuePair = currentKeyValuePair.getNext();
            }

            currentKeyValuePair.setNext(basicKeyValuePair);
        }
    }

    @Override
    public V get(final K key) {
        return table[keyIndexFromKey(key)].getValue();
    }

    @Override
    public boolean hasKey(final K key) {
        if (empty()) {
            return false;
        }

        for (KeyValuePair keyValuePair : table) {
            do {
                if (keyValuePair != null && keyValuePair.getKey().equals(key)) {
                    return true;
                }
                if (keyValuePair != null && keyValuePair.getNext() != null) {
                    keyValuePair = keyValuePair.getNext();
                }
            } while (keyValuePair != null && keyValuePair.getNext() != null);
        }

        return false;
    }

    @Override
    public boolean hasValue(final V value) {
        if (empty()) {
            return false;
        }

        for (KeyValuePair keyValuePair : table) {
            do {
                if (keyValuePair != null && keyValuePair.getValue().equals(value)) {
                    return true;
                }
                if (keyValuePair != null && keyValuePair.getNext() != null) {
                    keyValuePair = keyValuePair.getNext();
                }
            } while (keyValuePair != null && keyValuePair.getNext() != null);
        }

        return false;
    }

    @Override
    public void remove(final K key) {
        size--;
    }

    private int keyIndexFromKey(final K key) {
        int keyHash = hashFunction.hash(key);
        return Math.abs(keyHash) % table.length;
    }

    @Override
    public void resize() {
        float currentLoadFactor = loadFactor();
        int newSize = (int) Math.ceil((double)size/(double)DEFAULT_LOAD_FACTOR);

        if (currentLoadFactor > DEFAULT_LOAD_FACTOR || currentLoadFactor == 0) {
            KeyValuePair<K, V>[] tempTable = (KeyValuePair<K, V>[]) Array.newInstance(table.getClass().getComponentType(), newSize);

            int keyIndex;

            for (KeyValuePair<K, V> keyValuePair : table) {
                if (keyValuePair != null) {
                    keyIndex = keyIndexFromKey(keyValuePair.getKey());
                    tempTable[keyIndex] = keyValuePair;
                }
            }

            table = tempTable;
        }
    }
}
