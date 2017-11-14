package com.prince.oodesign.patterns.creational.prototype;

/**
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) {
        ConcretePrototype prototype1 = new ConcretePrototype();
        ConcretePrototype prototype2 = (ConcretePrototype)prototype1.clone();
    }
}
