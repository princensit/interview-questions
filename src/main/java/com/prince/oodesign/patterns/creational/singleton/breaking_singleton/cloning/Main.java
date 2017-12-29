package com.prince.oodesign.patterns.creational.singleton.breaking_singleton.cloning;

/**
 * Cloning can be used to create duplicate objects.
 *
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) {
        Singleton instance1 = Singleton.INSTANCE;
        Singleton instance2 = null;
        try {
            instance2 = (Singleton)instance1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println("Instance 1 hashcode: " + instance1.hashCode());
        System.out.println("Instance 2 hashcode: " + instance2.hashCode());
    }
}

// Singleton class
class Singleton extends SuperClass {

    // public instance initialized when loading the class
    public static final Singleton INSTANCE = new Singleton();

    private Singleton() {
        // private constructor
    }

    // ensures that cloning is not done
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}

class SuperClass implements Cloneable {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}