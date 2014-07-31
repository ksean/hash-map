package com.ks.hashmap;

public interface HashMap<K, V> {
    public int size();

    public float loadFactor();

    public boolean empty();
    public boolean hasKey(final K key);
    public boolean hasValue(final V value);

    public KeyValuePair<K, V>[] table();

    public HashFunction hashFunction();

    public void put(final K key, final V value);
    public void remove(final K key);
    public void resize();

    public V get(final K key);
}
