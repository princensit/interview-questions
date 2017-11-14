package com.prince.oodesign.patterns.creational.factory_method;

/**
 * @author Prince Raj
 */
public class ConcreteCreator extends Creator {

    @Override
    public Product factoryMethod() {
        return new ConcreteProduct();
    }
}
