package com.ks.hashmap;

public interface KeyValuePair<K, V> {
    public K getKey();
    public V getValue();
    public KeyValuePair getNext();
    public String getHash();
}
