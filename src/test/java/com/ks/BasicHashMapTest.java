package com.ks;


import com.ks.hashmap.basic.BasicHashMap;
import com.ks.hashmap.basic.BasicKeyValuePair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasicHashMapTest {

    @Test
    public void testTrueIsTrue() {
        assertEquals(true, true);
    }

    @Test
    public void hasZeroStartingSize() {
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>();

        assertEquals(basicHashMap.size(), 0);
    }

    @Test
    public void increasesInSizeAfterAddingKeyValuePair() {
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>();
        BasicKeyValuePair<String, Integer> basicKeyValuePair = new BasicKeyValuePair<String, Integer>("testKey", 1);

        basicHashMap.add(basicKeyValuePair);

        assertEquals(basicHashMap.size(), 1);
    }
}
