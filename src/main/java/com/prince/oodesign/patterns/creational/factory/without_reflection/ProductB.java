package com.prince.oodesign.patterns.creational.factory.without_reflection;

/**
 * @author Prince Raj
 */
public class ProductB extends Product {

    private final String name;

    static {
        Factory.getInstance().registerProduct("productId2", new ProductB("DEFAULT"));
    }

    private ProductB(String name) {
        this.name = name;
    }

    @Override
    public Product createProduct(String name) {
        return new ProductB(name);
    }

    @Override
    public void doSomething() {
        System.out.println("Product B");
    }
}
