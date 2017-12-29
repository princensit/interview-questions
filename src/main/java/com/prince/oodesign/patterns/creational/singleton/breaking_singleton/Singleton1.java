package com.prince.oodesign.patterns.creational.singleton.breaking_singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @author Prince Raj
 */
public class Singleton1 implements Serializable {

    private static final long serialVersionUID = 1234L;

    private static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Singleton instance already created!");
        }
    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }

    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }

    private Object writeReplace() throws ObjectStreamException {
        return INSTANCE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
