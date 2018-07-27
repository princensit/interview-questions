package com.prince.design.vending_machine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Prince Raj
 */
public class Inventory<T> {

    private Map<T, Integer> inventoryMap = new HashMap<>();

    public int getQuantity(T item) {
        Integer count = inventoryMap.get(item);
        if (count == null) {
            count = 0;
        }

        return count;
    }

    public void add(T item) {
        Integer count = inventoryMap.get(item);
        if (count == null) {
            count = 0;
        }

        count += 1;

        inventoryMap.put(item, count);
    }

    public void deduct(T item) {
        Integer count = inventoryMap.get(item);
        if (count == null) {
            count = 0;
        }

        count -= 1;

        inventoryMap.put(item, count);
    }

    public boolean isAvailable(T item) {
        return getQuantity(item) != 0;
    }

    public void put(T item, int count) {
        inventoryMap.put(item, count);
    }

    public void clear() {
        inventoryMap.clear();
    }
}
