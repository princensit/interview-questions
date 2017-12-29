package com.prince.oodesign.patterns.structural.decorator;

/**
 * @author Prince Raj
 */
public abstract class WindowDecorator implements Window {

    private Window windowToBeDecorated;

    public WindowDecorator(Window windowToBeDecorated) {
        this.windowToBeDecorated = windowToBeDecorated;
    }

    @Override
    public void draw() {
        windowToBeDecorated.draw();
    }

    @Override
    public String getDescription() {
        return windowToBeDecorated.getDescription();
    }
}
