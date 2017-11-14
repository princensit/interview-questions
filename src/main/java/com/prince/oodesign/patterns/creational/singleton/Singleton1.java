package com.prince.oodesign.patterns.creational.singleton;

import java.io.Serializable;

/**
 * Ex - Logger classes, Configuration classes, FactoryPatternUsingReflection classes, Accessed resources in shared mode
 *
 * <ul>
 * <li>Multi-threading - A special care should be taken when singleton has to be used in a multi-threading application.</li>
 * <li>Serialization - When Singletons are implementing Serializable interface they have to implement readResolve method
 * in order to avoid having 2 different objects.</li>
 * <li>Classloaders - If the Singleton class is loaded by 2 different class loaders we'll have 2 different classes, one
 * for each class loader.</li>
 * <li>Global Access Point represented by the class name - The singleton instance is obtained using the class name. At
 * the first view this is an easy way to access it, but it is not very flexible. If we need to replace the Singleton
 * class, all the references in the code should be changed accordingly.</li>
 * </ul>
 *
 * @author Prince Raj
 */
public class Singleton1 implements Serializable {

    // The serialization runtime associates with each serializable class a version number, called a serialVersionUID,
    // which is used during deserialization to verify that the sender and receiver of a serialized object have loaded
    // classes for that object that are compatible with respect to serialization. If the receiver has loaded a class for
    // the object that has a different serialVersionUID than that of the corresponding sender's class, then
    // deserialization will result in an InvalidClassException.

    // However, it is strongly recommended that all serializable classes explicitly declare serialVersionUID values,
    // since the default serialVersionUID computation is highly sensitive to class details that may vary depending on
    // compiler implementations, and can thus result in unexpected InvalidClassExceptions during deserialization.
    private static final long serialVersionUID = 42L;

    private static Singleton1 instance;

    // constructor - private
    private Singleton1() {
    }

    // See better version - double locking mechanism
    public static synchronized Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }

        return instance;
    }

    public void doSomething() {
        System.out.println("Hello");
    }

    private Object readResolve() {
        return instance;
    }
}
