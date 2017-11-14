package com.prince.oodesign.patterns.creational.factory.without_reflection;

/**
 * @author Prince Raj
 */
public class ProductA extends Product {

    private final String name;

    static {
        Factory.getInstance().registerProduct("productId1", new ProductA("DEFAULT"));
    }

    private ProductA(String name) {
        this.name = name;
    }

    @Override
    public Product createProduct(String name) {
        return new ProductA(name);
    }

    @Override
    public void doSomething() {
        System.out.println("Product A");
    }
}
