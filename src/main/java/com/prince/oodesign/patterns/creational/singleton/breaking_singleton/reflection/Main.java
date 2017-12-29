package com.prince.oodesign.patterns.creational.singleton.breaking_singleton.reflection;

import java.lang.reflect.Constructor;

/**
 * To prevent this, enums can be used as JVM ensures that enum value is initialized only once. Its only drawback that it
 * doesn't allows lazy initialization.
 *
 * public enum Singleton { INSTANCE; }
 *
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) {
        Singleton instance1 = Singleton.INSTANCE;
        Singleton instance2 = null;

        Constructor[] constructors = Singleton.class.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            // will break the singleton pattern
            constructor.setAccessible(true);
            try {
                instance2 = (Singleton)constructor.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Instance 1 hashcode: " + instance1.hashCode());
        System.out.println("Instance 2 hashcode: " + instance2.hashCode());
    }
}

// Singleton class
class Singleton {

    // public instance initialized when loading the class
    public static final Singleton INSTANCE = new Singleton();

    private Singleton() {
        // private constructor
    }
}