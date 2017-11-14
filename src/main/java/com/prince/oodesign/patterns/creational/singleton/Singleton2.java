package com.prince.oodesign.patterns.creational.singleton;

/**
 * @author Prince Raj
 */
public class Singleton2 {

    // early instantiation using implementation with static field.
    private static Singleton2 instance = new Singleton2();

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        return instance;
    }

    public void doSomething() {
        System.out.println("Hello");
    }
}
