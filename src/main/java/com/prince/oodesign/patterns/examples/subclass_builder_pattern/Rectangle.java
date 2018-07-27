package com.prince.oodesign.patterns.examples.subclass_builder_pattern;

/**
 * @author Prince Raj
 */
public class Rectangle extends Shape {

    private final double height;

    protected Rectangle(Init<?> init) {
        super(init);
        this.height = init.height;
    }

    public static class Builder extends Init<Builder> {

        @Override
        protected Builder self() {
            return this;
        }
    }

    private static abstract class Init<T extends Init<T>> extends Shape.Init<T> {

        private double height;

        public T height(double height) {
            this.height = height;
            return self();
        }

        public Rectangle build() {
            return new Rectangle(this);
        }
    }
}
