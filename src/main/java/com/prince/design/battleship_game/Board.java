package com.prince.design.battleship_game;

import com.prince.design.battleship_game.model.Cell;
import com.prince.design.battleship_game.model.Color;
import com.prince.design.battleship_game.model.Coordinate;
import com.prince.design.battleship_game.model.ShipType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Prince Raj
 */
public class Board {

    private Cell[][] cells;

    private Map<ShipType, Integer> remainingShips = new HashMap<ShipType, Integer>() {
        {
            put(ShipType.CARRIER, ShipType.CARRIER.getLength());
            put(ShipType.BATTLESHIP, ShipType.BATTLESHIP.getLength());
            put(ShipType.CRUISER, ShipType.CRUISER.getLength());
            put(ShipType.SUBMARINE, ShipType.SUBMARINE.getLength());
            put(ShipType.DESTROYER, ShipType.DESTROYER.getLength());
        }
    };

    public Board() {
        cells = new Cell[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = new Cell();
                Coordinate coordinate = new Coordinate(i, j);
                cell.setCoordinate(coordinate);
                cell.setColor(Color.BLU);

                cells[i][j] = cell;
            }
        }
    }

    public void mark(Coordinate coordinate, boolean hit) {
        int x = coordinate.getX();
        int y = coordinate.getY();

        Cell cell = cells[x][y];
        cell.setHit(hit);
        if (hit) {
            cell.setColor(Color.RED);
        } else {
            cell.setColor(Color.GRE);
        }
    }

    public boolean shoot(Coordinate coordinate) {
        boolean hit = false;

        int x = coordinate.getX();
        int y = coordinate.getY();

        Cell cell = cells[x][y];
        ShipType shipType = cell.getShipType();
        if (shipType != null && !cell.isHit()) {
            hit = true;
            cell.setHit(hit);
            cell.setColor(Color.RED);

            int count = remainingShips.get(shipType);
            if (count == 1) {
                remainingShips.remove(shipType);
            } else {
                remainingShips.put(shipType, count - 1);
            }
        } else {
            cell.setColor(Color.GRE);
        }

        return hit;
    }

    public boolean isAllShipsSunk() {
        return remainingShips.isEmpty();
    }

    public Cell[][] getCells() {
        return cells;
    }
}
