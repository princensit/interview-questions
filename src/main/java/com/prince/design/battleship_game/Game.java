package com.prince.design.battleship_game;

import com.prince.design.battleship_game.model.Coordinate;

/**
 * @author Prince Raj
 */
public class Game {

    private static Game instance;

    private Board board;

    private Player[] players;

    private Player winner = null;

    int turn = 0;

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }

        return instance;
    }

    public void initialize() {
        players = new Player[2];
        players[0] = new Player("P1");
        players[1] = new Player("P2");

        players[0].initializeShips();
        players[1].initializeShips();
    }

    public void play(String message) {
        Coordinate coordinate = validateAndGetCoordinate(message);

        Player currentPlayer = players[turn % 2];
        Player opponentPlayer = players[(turn + 1) % 2];

        boolean hit = opponentPlayer.shoot(coordinate);
        currentPlayer.mark(coordinate, hit);

        turn++;
    }

    private Coordinate validateAndGetCoordinate(String message) {
        if (message == null || message.length() < 2 || message.length() > 3) {
            throw new RuntimeException("Invalid input");
        }

        char[] charArray = message.toUpperCase().toCharArray();
        int x = charArray[0] - 'A';
        int y;
        if (charArray.length == 2) {
            y = Character.getNumericValue(charArray[1]);
        } else {
            y = Character.getNumericValue(charArray[1] + charArray[2]);
        }

        if (!(x >= 0 && x <= 9 && y >= 1 && y <= 10)) {
            throw new RuntimeException("Invalid input");
        }

        return new Coordinate(x, y - 1);
    }

    public boolean winnerFound() {
        boolean winnerFound = false;
        for (Player player : players) {
            Board board = player.getShipBoard();
            winnerFound = board.isAllShipsSunk();
            if (winnerFound) {
                winner = player;
                break;
            }
        }

        return winnerFound;
    }

    public String getWinnerPlayer() {
        String name = null;
        if (winner != null) {
            name = winner.getName();
        }

        return name;
    }

    public void printGame() {
        for (Player player : players) {
            System.out.println("\n--------------------------------------------------------\n");
            player.printBoards();
        }
    }
}
