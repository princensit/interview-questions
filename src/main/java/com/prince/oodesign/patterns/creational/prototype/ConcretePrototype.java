package com.prince.oodesign.patterns.creational.prototype;

/**
 * @author Prince Raj
 */
public class ConcretePrototype implements Prototype {

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
