package com.prince.oodesign.patterns.examples.subclass_builder_pattern;

/**
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) {
        Shape shape = new Shape.Builder().opacity(10.0).build();

        Rectangle rectangle = new Rectangle.Builder().height(10.0).opacity(12.0).build();
    }
}
