package com.prince.oodesign.patterns.creational.singleton;

/**
 * @author Prince Raj
 */
public class Singleton4 {

    private static class SingletonHolder {

        private static final Singleton4 instance = new Singleton4();
    }

    public static Singleton4 getInstance() {
        return SingletonHolder.instance;
    }
}
