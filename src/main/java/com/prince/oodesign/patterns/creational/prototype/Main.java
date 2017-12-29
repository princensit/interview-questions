package com.prince.oodesign.patterns.creational.prototype;

/**
 * Specify the kinds of objects to create using a prototypical instance, and create new objects from the 'skeleton' of
 * an existing object, thus boosting performance and keeping memory footprints to a minimum.
 *
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) {
        ConcretePrototype prototype1 = new ConcretePrototype();
        ConcretePrototype prototype2 = (ConcretePrototype)prototype1.clone();
        System.out.println(prototype2);
    }
}
