package com.ks;


import com.ks.hashmap.basic.BasicHashFunction;
import com.ks.hashmap.basic.BasicHashMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BasicHashMapTest {

    @Test
    public void hasZeroStartingSize() {
        // Setup
        BasicHashFunction basicHashFunction = new BasicHashFunction();
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>(basicHashFunction);

        // Assertions
        assertEquals(0, basicHashMap.size());
    }

    @Test
    public void increasesInSizeAfterPutKeyValuePair() {
        // Setup
        BasicHashFunction basicHashFunction = new BasicHashFunction();
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>(basicHashFunction);

        // Actions
        basicHashMap.put("testKey", 1);

        // Assertions
        assertEquals(1, basicHashMap.size());
    }

    @Test
    public void decreasesInSizeAfterRemoveKeyValuePair() {
        // Setup
        String testKey = "testKey";
        BasicHashFunction basicHashFunction = new BasicHashFunction();
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>(basicHashFunction);

        // Actions
        basicHashMap.put(testKey, 1);
        basicHashMap.remove(testKey);

        // Assertions
        assertEquals(0, basicHashMap.size());
    }

    @Test
    public void keyDoesNotExistAfterRemoveKeyValuePair() {
        // Setup
        String testKey = "testKey";
        BasicHashFunction basicHashFunction = new BasicHashFunction();
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>(basicHashFunction);

        // Actions
        basicHashMap.put(testKey, 1);
        basicHashMap.remove(testKey);

        // Assertions
        assertFalse(basicHashMap.hasKey(testKey));
    }

    @Test
    public void hasNoKeyBeforePutKeyValuePair() {
        // Setup
        BasicHashFunction basicHashFunction = new BasicHashFunction();
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>(basicHashFunction);

        // Assertions
        assertFalse(basicHashMap.hasKey("testKey"));
    }

    @Test
    public void hasKeyAfterPutKeyValuePair() {
        // Setup
        String testKey = "testKey";
        BasicHashFunction basicHashFunction = new BasicHashFunction();
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>(basicHashFunction);

        // Actions
        basicHashMap.put(testKey, 1);

        // Assertions
        assertTrue(basicHashMap.hasKey(testKey)); // By reference
        assertTrue(basicHashMap.hasKey("testKey")); // By object
    }

    @Test
    public void hasNoValueBeforePutKeyValuePair() {
        // Setup
        BasicHashFunction basicHashFunction = new BasicHashFunction();
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>(basicHashFunction);

        // Assertions
        assertFalse(basicHashMap.hasValue(1));
    }

    @Test
    public void hasValueAfterPutKeyValuePair() {
        // Setup
        int testValue = 1;
        BasicHashFunction basicHashFunction = new BasicHashFunction();
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>(basicHashFunction);

        // Actions
        basicHashMap.put("testKey", testValue);

        // Assertions
        assertTrue(basicHashMap.hasValue(testValue)); // By reference
        assertTrue(basicHashMap.hasValue(1)); // By object
    }

    @Test
    public void canGetValueByKeyAfterPutKeyValuePair() {
        // Setup
        int testValue = 1;
        String testKey = "testKey";
        BasicHashFunction basicHashFunction = new BasicHashFunction();
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>(basicHashFunction);

        // Actions
        basicHashMap.put(testKey, testValue);

        // Assertions
        assertTrue(basicHashMap.get(testKey).equals(testValue));
    }

    @Test
    public void cantGetValueByKeyAfterRemoveKeyValuePair() {
        // Setup
        int testValue = 1;
        String testKey = "testKey";
        BasicHashFunction basicHashFunction = new BasicHashFunction();
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>(basicHashFunction);

        // Actions
        basicHashMap.put(testKey, testValue);
        basicHashMap.remove(testKey);

        // Assertions
        assertFalse(basicHashMap.hasKey(testKey));
    }

    @Test
    public void putSameKeyTwiceHasSizeOne() {
        // Setup
        int firstValue = 1;
        int secondValue = 2;
        String testKey = "testKey";
        BasicHashFunction basicHashFunction = new BasicHashFunction();
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>(basicHashFunction);

        // Actions
        basicHashMap.put(testKey, firstValue);
        basicHashMap.put(testKey, secondValue);

        // Assertions
        assertEquals(1, basicHashMap.size());
    }

    @Test
    public void putSameKeyReturnsLatestPutValue() {
        // Setup
        int firstValue = 1;
        int secondValue = 2;
        String testKey = "testKey";
        BasicHashFunction basicHashFunction = new BasicHashFunction();
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>(basicHashFunction);

        // Actions
        basicHashMap.put(testKey, firstValue);
        basicHashMap.put(testKey, secondValue);

        // Assertions
        assertTrue(basicHashMap.get(testKey).equals(secondValue));
    }

    @Test
    public void putTwoKeyValuePairsCanGetFirstValue() {
        // Setup
        int firstValue = 1;
        int secondValue = 2;
        String firstKey = "firstKey";
        String secondKey = "secondKey";
        BasicHashFunction basicHashFunction = new BasicHashFunction();
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>(basicHashFunction);

        // Actions
        basicHashMap.put(firstKey, firstValue);
        basicHashMap.put(secondKey, secondValue);

        // Assertions
        assertTrue(basicHashMap.get(firstKey).equals(firstValue));
    }

    @Test
    public void putTwoKeyValuePairsHasSizeTwo() {
        // Setup
        int firstValue = 1;
        int secondValue = 2;
        String firstKey = "firstKey";
        String secondKey = "secondKey";
        BasicHashFunction basicHashFunction = new BasicHashFunction();
        BasicHashMap<String, Integer> basicHashMap = new BasicHashMap<String, Integer>(basicHashFunction);

        // Actions
        basicHashMap.put(firstKey, firstValue);
        basicHashMap.put(secondKey, secondValue);

        // Assertions
        assertEquals(2, basicHashMap.size());
    }
}
