package com.prince.oodesign.patterns.structural.decorator;

/**
 * @author Prince Raj
 */
public class SimpleWindow implements Window {

    @Override
    public void draw() {
        // draw window
    }

    @Override
    public String getDescription() {
        return "simple window";
    }
}
