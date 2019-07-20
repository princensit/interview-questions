package com.prince.design.battleship_game.model;

/**
 * @author Prince Raj
 */
public enum ShipType {

    CARRIER(5, 'A'), BATTLESHIP(4, 'B'), CRUISER(3, 'C'), DESTROYER(2, 'D'), SUBMARINE(3, 'S');

    private final int length;

    private final char shortName;

    ShipType(int length, char shortName) {
        this.length = length;
        this.shortName = shortName;
    }

    public int getLength() {
        return length;
    }

    public char getShortName() {
        return shortName;
    }
}
