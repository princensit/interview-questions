package com.prince.oodesign.patterns.structural.decorator;

/**
 * Statically or dynamically adds responsibility to the interface by wrapping the original code without affecting the
 * behaviour of other objects from the same class. This is often useful for adhering to the single responsibility
 * principle, as it allows functionality to be divided between classes with unique areas of concern.
 *
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) {
        Car myTesla = new ModelS();
        myTesla = new EnhancedAutoPilot(myTesla);

        System.out.println(myTesla.getDescription());
        System.out.println(myTesla.getCost());
    }
}
