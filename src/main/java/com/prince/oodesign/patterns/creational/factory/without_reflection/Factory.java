package com.prince.oodesign.patterns.creational.factory.without_reflection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Prince Raj
 */
public class Factory {

    private static Factory instance;

    private final Map<String, Product> registeredProducts = new HashMap<>();

    // private constructor
    private Factory() {

    }

    public static Factory getInstance() {
        if (instance == null) {
            synchronized (Factory.class) {
                if (instance == null) {
                    instance = new Factory();
                }
            }
        }

        return instance;
    }

    void registerProduct(String productId, Product product) {
        registeredProducts.put(productId, product);
    }

    Product createProduct(String productId) {
        Product product = registeredProducts.get(productId);
        return product.createProduct(productId);
    }
}
