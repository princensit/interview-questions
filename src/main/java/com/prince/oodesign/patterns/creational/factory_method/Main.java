package com.prince.oodesign.patterns.creational.factory_method;

/**
 * http://www.oodesign.com/factory-method-pattern.html
 *
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) {
        ConcreteCreator creator = new ConcreteCreator();
        Product product = creator.factoryMethod();
        product.doSomething();
    }
}
