package com.ks;


import com.ks.hashmap.basic.BasicHashMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BasicHashMapTest {

    @Test
    public void hasZeroStartingSize() {
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>();

        assertEquals(basicHashMap.size(), 0);
    }

    @Test
    public void increasesInSizeAfterPutKeyValuePair() {
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>();

        basicHashMap.put("testKey", 1);

        assertEquals(basicHashMap.size(), 1);
    }

    @Test
    public void decreasesInSizeAfterRemoveKeyValuePair() {
        String testKey = "testKey";
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>();

        basicHashMap.put(testKey, 1);

        basicHashMap.remove(testKey);

        assertEquals(basicHashMap.size(), 0);
    }

    @Test
    public void hasNoKeyBeforePutKeyValuePair() {
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>();

        assertFalse(basicHashMap.hasKey("testKey"));
    }

    @Test
    public void hasKeyAfterPutKeyValuePair() {
        String testKey = "testKey";

        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>();

        basicHashMap.put(testKey, 1);

        assertTrue(basicHashMap.hasKey(testKey)); // By reference
        assertTrue(basicHashMap.hasKey("testKey")); // By object
    }

    @Test
    public void hasNoValueBeforePutKeyValuePair() {
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>();

        assertFalse(basicHashMap.hasValue(1));
    }

    @Test
    public void hasValueAfterPutKeyValuePair() {
        int testValue = 1;

        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>();

        basicHashMap.put("testKey", testValue);

        assertTrue(basicHashMap.hasValue(testValue)); // By reference
        assertTrue(basicHashMap.hasValue(1)); // By object
    }

}
