package com.prince.oodesign.patterns.creational.factory.reflection;

/**
 * @author Prince Raj
 */
public class Main {

    // We have to make sure that the concrete product classes are loaded before they are required by the factory for
    // registration (if they are not loaded, then they will not be registered in the factory and createProduct will
    // return null
    static {
        try {
            Class.forName("com.oodesign.patterns.creational.factory.reflection.ProductA");
            Class.forName("com.oodesign.patterns.creational.factory.reflection.ProductB");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Factory factory = Factory.getInstance();

        Product productA = factory.createProduct("productId1");
        Product productB = factory.createProduct("productId2");

        productA.doSomething();
        productB.doSomething();
    }
}
