package com.prince.design.vending_machine;

/**
 * @author Prince Raj
 */
public class VendingMachineFactory {

    private static VendingMachineFactory instance;

    // private constructor
    private VendingMachineFactory() {
    }

    public static VendingMachineFactory getInstance() {
        if (instance == null) {
            synchronized (VendingMachineFactory.class) {
                if (instance == null) {
                    instance = new VendingMachineFactory();
                }
            }
        }

        return instance;
    }

    public VendingMachine getVendingMachine() {
        return new VendingMachineImpl();
    }
}
