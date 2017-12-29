package com.prince.oodesign.patterns.structural.adapter;

/**
 * @author Prince Raj
 */
public class LightningPortToMicroUSBAdapter implements Android {

    private final IPhone iPhone;

    public LightningPortToMicroUSBAdapter(IPhone iPhone) {
        this.iPhone = iPhone;
    }

    @Override
    public void recharge() {
        iPhone.recharge();
    }

    @Override
    public void useMicroUSB() {
        System.out.println("Want to use micro USB, converting to lightning port...");
        iPhone.useLightningPort();
    }
}
