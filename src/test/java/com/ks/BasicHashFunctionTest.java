package com.ks;

import com.ks.hashmap.HashFunction;
import com.ks.hashmap.basic.BasicHashFunction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicHashFunctionTest {
    @Test
    public void deterministic() {
        String testString = "foo";
        HashFunction hashFunction = new BasicHashFunction();

        String firstHash = hashFunction.hash(testString);
        String secondHash = hashFunction.hash(testString);

        assertEquals(firstHash, secondHash);
    }
}
