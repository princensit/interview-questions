package com.prince.oodesign.patterns.structural.adapter;

/**
 * @author Prince Raj
 */
public class GooglePixel implements Android{

    @Override
    public void recharge() {
        System.out.println("Recharging...");
    }

    @Override
    public void useMicroUSB() {
        System.out.println("Using micro USB...");
    }
}
