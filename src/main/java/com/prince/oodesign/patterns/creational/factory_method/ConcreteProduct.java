package com.prince.oodesign.patterns.creational.factory_method;

/**
 * @author Prince Raj
 */
public class ConcreteProduct implements Product {

    @Override
    public void doSomething() {
        System.out.println("Concrete product");
    }
}
