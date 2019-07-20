package com.prince.design.battleship_game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.prince.design.battleship_game.model.Cell;
import com.prince.design.battleship_game.model.Color;
import com.prince.design.battleship_game.model.Coordinate;
import com.prince.design.battleship_game.model.ShipType;
import lombok.Data;

/**
 * @author Prince Raj
 */
public class Player {

    private final String name;

    private Board shipBoard = new Board();

    private Board missileTrackerBoard = new Board();

    private Random random = new Random();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void initializeShips() {
        Cell[][] cells = shipBoard.getCells();

        boolean horizontalDirection = true;

        Set<Index> indexSet = new HashSet<>();
        for (ShipType shipType : ShipType.values()) {
            int length = shipType.getLength();

            List<Index> indexArray = getRandomIndexArray(length, indexSet, horizontalDirection);
            for (Index index : indexArray) {
                int x = index.getX();
                int y = index.getY();

                Cell cell = cells[x][y];
                Coordinate coordinate = new Coordinate(x, y);
                cell.setCoordinate(coordinate);
                cell.setShipType(shipType);
            }

            horizontalDirection = !horizontalDirection;
        }
    }

    public Board getShipBoard() {
        return shipBoard;
    }

    public void mark(Coordinate coordinate, boolean hit) {
        missileTrackerBoard.mark(coordinate, hit);
    }

    public boolean shoot(Coordinate coordinate) {
        return shipBoard.shoot(coordinate);
    }

    public void printBoards() {
        Cell[][] shipBoardCells = shipBoard.getCells();
        Cell[][] attackedBoardCells = missileTrackerBoard.getCells();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = shipBoardCells[i][j];
                ShipType shipType = cell.getShipType();
                System.out.print("(" + (shipType != null ? shipType.getShortName() : "-") + ":"
                        + cell.getColor().name() + ")");
                if (j != 9) {
                    System.out.print(" ");
                }
            }

            System.out.print("        ");

            for (int j = 0; j < 10; j++) {
                Cell cell = attackedBoardCells[i][j];
                System.out
                        .print(Color.BLU.equals(cell.getColor()) ? " - " : cell.getColor().name());
                if (j != 9) {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    private List<Index> getRandomIndexArray(int length, Set<Index> indexSet,
            boolean horizontalDirection) {

        List<Index> indexArray = null;

        boolean retry = true;
        while (retry) {
            indexArray = new ArrayList<>(length);

            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (horizontalDirection) {
                if (y + length <= 9) {
                    for (int i = 0; i < length; i++) {
                        Index index = new Index(x, y + i);
                        if (indexSet.contains(index)) {
                            break;
                        } else {
                            indexArray.add(index);

                            if (indexArray.size() == length) {
                                indexSet.addAll(indexArray);
                                retry = false;
                            }
                        }
                    }
                }
            } else {
                if (x + length <= 9) {
                    for (int i = 0; i < length; i++) {
                        Index index = new Index(x + i, y);
                        if (indexSet.contains(index)) {
                            break;
                        } else {
                            indexArray.add(index);

                            if (indexArray.size() == length) {
                                indexSet.addAll(indexArray);
                                retry = false;
                            }
                        }
                    }
                }
            }
        }

        return indexArray;
    }

    @Data
    private static class Index {

        private final int x;

        private final int y;

        private Index(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
