package com.prince.design;

/**
 * @author Prince Raj
 */
public class ParkingLot {

    // number of floors
    private static final int LEVELS_COUNT = 3;

    private Level[] levels;

    public boolean parkVehicle(Vehicle vehicle) {
        // TODO Implement me!

        return true;
    }
}

class ParkingSpot {

    private Vehicle vehicle;

    private VehicleSize vehicleSize;

    private int row;

    private int spotNumber;

    private Level level;

    public boolean isAvailable() {
        return vehicle == null;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        // TODO Implement me!

        return true;
    }

    public boolean park(Vehicle vehicle) {
        // TODO Implement me!

        return true;
    }

    public boolean removeVehicle(Vehicle vehicle) {
        // TODO Implement me!

        return true;
    }

    public int getRow() {
        return row;
    }

    public int getSpotNumber() {
        return spotNumber;
    }
}

class Level {

    private static final int SPOTS_PER_ROW = 10;

    private int floor;

    private ParkingSpot[] parkingSpots;

    private int availableSpots = 0;

    public boolean parkVehicle(Vehicle vehicle) {
        // TODO Implement me!
        availableSpots--;

        return true;
    }

    public boolean parkStartingAtSpot(Vehicle v, int num) {
        // TODO Implement me!
        availableSpots--;

        return true;
    }

    public int findAvailableSpot(Vehicle vehicle) {
        // TODO Implement me!

        return 1;
    }

    public void spotFreed() {
        availableSpots++;
    }
}

abstract class Vehicle {

    private String vehicleNo;

    private VehicleSize vehicleSize;

    private int spotsNeeded;

    public Vehicle(VehicleSize vehicleSize, int spotsNeeded) {
        this.vehicleSize = vehicleSize;
        this.spotsNeeded = spotsNeeded;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public boolean canFitInSpot(ParkingSpot spot) {
        // TODO Implement me!

        return true;
    }
}

class Bus extends Vehicle {

    public Bus(VehicleSize vehicleSize) {
        super(vehicleSize, 5);
    }
}

class Car extends Vehicle {

    public Car(VehicleSize vehicleSize) {
        super(vehicleSize, 1);
    }
}

class MotorCycle extends Vehicle {

    public MotorCycle(VehicleSize vehicleSize) {
        super(vehicleSize, 1);
    }
}

enum VehicleSize {
    MOTORCYCLE, COMPACT, LARGE
}