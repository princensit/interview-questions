package com.prince.oodesign.patterns.creational.factory_method;

/**
 * @author Prince Raj
 */
public abstract class Creator {

    public void someOperation() {
        Product product = factoryMethod();
        product.doSomething();
    }

    public abstract Product factoryMethod();
}
