package com.prince.oodesign.patterns.examples.subclass_builder_pattern;

/**
 * @author Prince Raj
 */
public class Shape {

    private final double opacity;

    protected Shape(Init<?> init) {
        this.opacity = init.opacity;
    }

    public static class Builder extends Init<Builder> {

        @Override
        protected Builder self() {
            return this;
        }
    }

    protected static abstract class Init<T extends Init<T>> {

        private double opacity;

        protected abstract T self();

        public T opacity(double opacity) {
            this.opacity = opacity;
            return self();
        }

        public Shape build() {
            return new Shape(this);
        }
    }
}
