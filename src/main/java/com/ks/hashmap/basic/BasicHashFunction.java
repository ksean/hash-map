package com.ks.hashmap.basic;

import com.ks.hashmap.HashFunction;

public class BasicHashFunction implements HashFunction {

    @Override
    public int hash(Object object) {
        return object.hashCode();
    }
}