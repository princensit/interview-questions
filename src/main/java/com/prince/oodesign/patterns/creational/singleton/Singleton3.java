package com.prince.oodesign.patterns.creational.singleton;

/**
 * @author Prince Raj
 */
public class Singleton3 {

    private static Singleton3 instance;

    public static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }

        return instance;
    }

    public void doSomething() {
        System.out.println("Hello");
    }
}
