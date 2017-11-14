package com.prince.oodesign.patterns.creational.factory.reflection;

/**
 * @author Prince Raj
 */
public class ProductA implements Product {

    private final String name;

    static {
        Factory.getInstance().registerProduct("productId1", ProductA.class);
    }

    public ProductA(String name) {
        this.name = name;
    }

    @Override
    public void doSomething() {
        System.out.println("Product A");
    }
}
