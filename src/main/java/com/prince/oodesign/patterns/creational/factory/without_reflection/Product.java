package com.prince.oodesign.patterns.creational.factory.without_reflection;

/**
 * @author Prince Raj
 */
public abstract class Product {

    public abstract Product createProduct(String name);

    public abstract void doSomething();
}
