package com.prince.oodesign.patterns.creational.factory.reflection;

/**
 * @author Prince Raj
 */
public class ProductB implements Product {

    private final String name;

    static {
        Factory.getInstance().registerProduct("productId2", ProductB.class);
    }

    public ProductB(String name) {
        this.name = name;
    }

    @Override
    public void doSomething() {
        System.out.println("Product B");
    }
}
