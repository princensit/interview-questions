package com.prince.oodesign.patterns.creational.factory_method;

/**
 * Define an interface for creating a single object, but let subclasses decide which class to instantiate. Factory
 * Method lets a class defer instantiation to subclasses.
 *
 * http://www.oodesign.com/factory-method-pattern.html
 *
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        creator.someOperation();

        Product product = creator.factoryMethod();
        product.doSomething();
    }
}
