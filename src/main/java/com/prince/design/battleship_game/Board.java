package com.prince.design.battleship_game;

import com.prince.design.battleship_game.model.Cell;
import com.prince.design.battleship_game.model.Coordinate;

/**
 * @author Prince Raj
 */
public interface Board {

    /**
     * get all cells from given board
     *
     * @return cells matrix
     */
    Cell[][] getCells();

    /**
     * Opponent shoot at particular coordinate
     *
     * @param coordinate coordinate
     * @return status if it is a hit or miss
     */
    boolean shoot(Coordinate coordinate);

    /**
     * returns if opponent has destroyed all ships
     *
     * @return status if all ships are destroyed or not
     */
    boolean isAllShipsSunk();

    /**
     * used to track the missiles that has been fired
     *
     * @param coordinate coordinate
     * @param hit if true then hit else miss
     */
    void mark(Coordinate coordinate, boolean hit);
}
