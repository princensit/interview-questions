package com.prince.design.battleship_game;

import com.prince.design.battleship_game.model.Cell;
import com.prince.design.battleship_game.model.Color;
import com.prince.design.battleship_game.model.Coordinate;

/**
 * @author Prince Raj
 */
public class MissileTrackerBoard extends AbstractBoard implements Board {

    @Override
    public boolean shoot(Coordinate coordinate) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public boolean isAllShipsSunk() {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public void mark(Coordinate coordinate, boolean hit) {
        int x = coordinate.getX();
        int y = coordinate.getY();

        Cell[][] cells = getCells();
        Cell cell = cells[x][y];
        cell.setHit(hit);
        if (hit) {
            cell.setColor(Color.RED);
        } else {
            cell.setColor(Color.GRE);
        }
    }
}
