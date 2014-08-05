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
        // Create new key value pair for put
        BasicKeyValuePair<K, V> basicKeyValuePair = new BasicKeyValuePair<K, V>(key, value, null);
        int keyIndex;

        if (!hasKey(key)) {
            size++;
            resize();
            keyIndex = keyIndexFromKey(key);

            putInBucket(keyIndex, basicKeyValuePair, table);
        } else {
            keyIndex = keyIndexFromKey(key);

            replaceInBucket(keyIndex, basicKeyValuePair);
        }
    }

    private void replaceInBucket(int keyIndex, KeyValuePair<K, V> newKeyValuePair) {
        KeyValuePair<K, V> startingKeyValuePair = table[keyIndex];

        // Check if the first key value pair matches the new one
        if (startingKeyValuePair.getKey().equals(newKeyValuePair.getKey())) {
            // Maintain list consistency
            if (startingKeyValuePair.getNext() != null) {
                newKeyValuePair.setNext(startingKeyValuePair.getNext());
            }

            table[keyIndex] = newKeyValuePair;

        // If it doesn't match the first key value pair, iterate through until it is found
        } else {
            // Starting initialization to remember the previous key value pair
            KeyValuePair<K, V> previousKeyValuePair = startingKeyValuePair;
            KeyValuePair<K, V> nextKeyValuePair = previousKeyValuePair.getNext();

            // This loop should always be broken or else the key didn't exist and won't be put
            while (nextKeyValuePair != null) {
                // Check if the next key matches the new key to be put
                if (nextKeyValuePair.getKey().equals(newKeyValuePair.getKey())) {
                    // Maintain list consistency
                    if (nextKeyValuePair.getNext() != null) {
                        newKeyValuePair.setNext(nextKeyValuePair.getNext());
                    }

                    // Replace the current key by pointing the previous pair at it
                    previousKeyValuePair.setNext(newKeyValuePair);
                    break;
                } else {
                    // Reassign the next and previous pairs
                    KeyValuePair<K, V> temporaryKeyValuePair = nextKeyValuePair;
                    previousKeyValuePair = nextKeyValuePair;
                    nextKeyValuePair = temporaryKeyValuePair.getNext();
                }
            }
        }
    }

    @Override
    public V get(final K key) {
        if (!hasKey(key)) {
            return null;
        }

        KeyValuePair<K, V> currentKeyValuePair = table[keyIndexFromKey(key)];

        while (!currentKeyValuePair.getKey().equals(key)) {
            currentKeyValuePair = currentKeyValuePair.getNext();
        }

        return currentKeyValuePair.getValue();
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

    private int keyIndexFromKey(final K key, KeyValuePair<K, V>[] referenceTable) {
        int keyHash = hashFunction.hash(key);
        return Math.abs(keyHash) % referenceTable.length;
    }

    private int keyIndexFromKey(final K key) {
        return keyIndexFromKey(key, table);
    }

    private void resize() {
        float currentLoadFactor = loadFactor();

        if (currentLoadFactor > DEFAULT_LOAD_FACTOR || currentLoadFactor == 0) {
            int newSize = (int) Math.ceil((double)size/(double)DEFAULT_LOAD_FACTOR);
            KeyValuePair<K, V>[] tempTable = (KeyValuePair<K, V>[]) Array.newInstance(table.getClass().getComponentType(), newSize);

            int keyIndex;

            for (KeyValuePair<K, V> keyValuePair : table) {
                if (keyValuePair != null) {
                    keyValuePair.setNext(null);
                    keyIndex = keyIndexFromKey(keyValuePair.getKey(), tempTable);
                    putInBucket(keyIndex, keyValuePair, tempTable);
                }
            }

            table = tempTable;
        }
    }

    private void putInBucket(int keyIndex, KeyValuePair<K, V> newKeyValuePair, KeyValuePair<K, V>[] referenceTable) {
        KeyValuePair<K, V> currentKeyValuePair = referenceTable[keyIndex];

        if (currentKeyValuePair == null) {
            referenceTable[keyIndex] = newKeyValuePair;

        } else {
            while (currentKeyValuePair.getNext() != null) {
                currentKeyValuePair = currentKeyValuePair.getNext();
            }

            currentKeyValuePair.setNext(newKeyValuePair);
        }
    }
}
