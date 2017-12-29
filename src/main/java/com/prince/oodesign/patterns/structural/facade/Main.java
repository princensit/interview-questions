package com.prince.oodesign.patterns.structural.facade;

/**
 * Provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes
 * the subsystem easier to use.
 *
 * @author Prince Raj
 */

/* Client */

class Main {

    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}

/* Complex parts */

class CPU {

    public void freeze() {
    }

    public void jump(long position) {
    }

    public void execute() {
    }
}

class HardDrive {

    public byte[] read(long lba, int size) {
        return null;
    }
}

class Memory {

    public void load(long position, byte[] data) {
    }
}

/* Facade */

class ComputerFacade {

    private CPU processor;

    private Memory ram;

    private HardDrive hd;

    public ComputerFacade() {
        this.processor = new CPU();
        this.ram = new Memory();
        this.hd = new HardDrive();
    }

    public void start() {
        processor.freeze();
        long bootAddress = 10;
        long bootSector = 20;
        int sectorSize = 5;
        ram.load(bootAddress, hd.read(bootSector, sectorSize));
        processor.jump(bootAddress);
        processor.execute();
    }
}
