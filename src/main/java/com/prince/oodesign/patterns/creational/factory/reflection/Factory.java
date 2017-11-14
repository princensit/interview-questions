package com.prince.oodesign.patterns.creational.factory.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory class is singleton.
 *
 * The procedural implementation i.e. if-else is the classical bad example for the Open-Close Principle.
 *
 * This reflection implementation has its own drawbacks. The main one is performance. When the reflection is used the
 * performance on code involving reflection can decrease even to 10% of the performance of a non reflection code.
 * Another issue is that not all the programming languages provide reflection mechanism.
 *
 * @author Prince Raj
 */
public class Factory {

    private static Factory instance;

    private final Map<String, Class<? extends Product>> registeredProducts = new HashMap<>();

    // private constructor
    private Factory() {

    }

    static Factory getInstance() {
        if (instance == null) {
            synchronized (Factory.class) {
                if (instance == null) {
                    instance = new Factory();
                }
            }
        }

        return instance;
    }

    void registerProduct(String productId, Class<? extends Product> productClass) {
        registeredProducts.put(productId, productClass);
    }

    @SuppressWarnings("unchecked")
    Product createProduct(String productId) throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        Class<Product> productClass = (Class<Product>)registeredProducts.get(productId);
        if (productClass == null) {
            throw new RuntimeException("Product class not registered for productId: " + productId);
        }

        Constructor<Product> constructor = productClass.getConstructor(String.class);
        return constructor.newInstance(productId);
    }
}
