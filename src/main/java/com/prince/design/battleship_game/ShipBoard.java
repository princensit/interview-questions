package com.prince.design.battleship_game;

import java.util.HashMap;
import java.util.Map;

import com.prince.design.battleship_game.model.Cell;
import com.prince.design.battleship_game.model.Color;
import com.prince.design.battleship_game.model.Coordinate;
import com.prince.design.battleship_game.model.ShipType;

/**
 * @author Prince Raj
 */
public class ShipBoard extends AbstractBoard implements Board {

    private Map<ShipType, Integer> remainingShips = new HashMap<ShipType, Integer>() {
        {
            put(ShipType.CARRIER, ShipType.CARRIER.getLength());
            put(ShipType.BATTLESHIP, ShipType.BATTLESHIP.getLength());
            put(ShipType.CRUISER, ShipType.CRUISER.getLength());
            put(ShipType.SUBMARINE, ShipType.SUBMARINE.getLength());
            put(ShipType.DESTROYER, ShipType.DESTROYER.getLength());
        }
    };

    public ShipBoard() {
        super();
    }

    @Override
    public boolean shoot(Coordinate coordinate) {
        boolean hit = false;

        int x = coordinate.getX();
        int y = coordinate.getY();

        Cell[][] cells = getCells();
        Cell cell = cells[x][y];
        ShipType shipType = cell.getShipType();
        if (shipType != null) {
            hit = true;
            if (!cell.isHit()) {
                cell.setHit(hit);
                cell.setColor(Color.RED);

                int count = remainingShips.get(shipType);
                if (count == 1) {
                    remainingShips.remove(shipType);
                } else {
                    remainingShips.put(shipType, count - 1);
                }
            }
        } else {
            cell.setColor(Color.GRE);
        }

        return hit;
    }

    @Override
    public boolean isAllShipsSunk() {
        return remainingShips.isEmpty();
    }

    @Override
    public void mark(Coordinate coordinate, boolean hit) {
        throw new UnsupportedOperationException("Unsupported");
    }
}
