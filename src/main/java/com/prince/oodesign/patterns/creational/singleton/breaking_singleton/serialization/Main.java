package com.prince.oodesign.patterns.creational.singleton.breaking_singleton.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Serialization is used to convert an object of byte stream and save in a file or send over a network. Suppose you
 * serialize an object of a singleton class, then if you de-serialize that object it will create a new instance and
 * hence break the singleton pattern.
 *
 * Enums by default prevent serialization issues
 *
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) {
        Singleton instance1 = Singleton.INSTANCE;

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("/tmp/test.txt"))) {
            objectOutputStream.writeObject(instance1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Singleton instance2 = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("/tmp/test.txt"))) {
            instance2 = (Singleton)objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Instance 1 hashcode: " + instance1.hashCode());
        System.out.println("Instance 2 hashcode: " + instance2.hashCode());
    }
}

// Singleton class
class Singleton implements Serializable {

    private static final long serialVersionUID = 1234L;

    // public instance initialized when loading the class
    public static final Singleton INSTANCE = new Singleton();

    private Singleton() {
        // private constructor
    }

    // readResolve is used for replacing the object read from the stream. This is only used for enforcing singletons.
    protected Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }
}
