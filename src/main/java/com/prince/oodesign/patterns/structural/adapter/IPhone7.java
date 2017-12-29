package com.prince.oodesign.patterns.structural.adapter;

/**
 * @author Prince Raj
 */
public class IPhone7 implements IPhone {

    @Override
    public void recharge() {
        System.out.println("Recharging...");
    }

    @Override
    public void useLightningPort() {
        System.out.println("Using lightning port...");
    }
}
