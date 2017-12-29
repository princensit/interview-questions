package com.prince.oodesign.patterns.structural.adapter;

/**
 * Adapter or Wrapper -> Converts one interface to another so that it matches what the client is expecting
 *
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) {
        IPhone iPhone = new IPhone7();

        LightningPortToMicroUSBAdapter adapter = new LightningPortToMicroUSBAdapter(iPhone);
        adapter.useMicroUSB();
        adapter.recharge();
    }
}
