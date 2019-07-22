package com.prince.design.battleship_game;

import com.prince.design.battleship_game.model.Cell;
import com.prince.design.battleship_game.model.Color;
import com.prince.design.battleship_game.model.Coordinate;

/**
 * @author Prince Raj
 */
public abstract class AbstractBoard {

    private Cell[][] cells;

    public AbstractBoard() {
        cells = new Cell[10][10];

        // initializing all the cells with coordinate and colour as BLUE
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

    public Cell[][] getCells() {
        return cells;
    }
}
