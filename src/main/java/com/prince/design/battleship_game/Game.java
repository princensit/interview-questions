package com.prince.design.battleship_game;

import com.prince.design.battleship_game.model.Coordinate;

/**
 * @author Prince Raj
 */
public class Game {

    private static Game instance;

    private Player[] players;

    private Player winner = null;

    private int turn = 0;

    /**
     * Used to create singleton object for this game.
     *
     * @return game object
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }

        return instance;
    }

    /**
     * Initializing players and its ships
     */
    public void initialize() {
        players = new Player[2];
        players[0] = new Player("P1");
        players[1] = new Player("P2");

        players[0].initializeShips();
        players[1].initializeShips();
    }

    /**
     * Each player in turn shoot the ships of opponent player. If it is hit or miss, then player
     * mark it in his tracker board accordingly
     *
     * @param input
     */
    public void play(String input) {
        Coordinate coordinate = validateAndGetCoordinate(input);

        Player currentPlayer = players[turn % 2];
        Player opponentPlayer = players[(turn + 1) % 2];

        boolean hit = opponentPlayer.shoot(coordinate);
        currentPlayer.mark(coordinate, hit);

        turn++;
    }

    /**
     * checks if winner is found or not
     *
     * @return returns true if winner is found i.e. player has destroyed all ships of opponents
     */
    public boolean winnerFound() {
        boolean winnerFound = false;
        for (int i = 0; i < players.length; i++) {
            Board board = players[i].getShipBoard();
            winnerFound = board.isAllShipsSunk();
            if (winnerFound) {
                if (i == 0) {
                    winner = players[1];
                } else {
                    winner = players[0];
                }

                drawGame();
                break;
            }
        }

        return winnerFound;
    }

    /**
     * returns name of winning player
     *
     * @return returns the name of winning player
     */
    public String getWinnerPlayer() {
        String name = null;
        if (winner != null) {
            name = winner.getName();
        }

        return name;
    }

    /**
     * prints both the ship and missile tracker board for each player
     */
    public void drawGame() {
        for (Player player : players) {
            System.out.println("\n--------------------------------------------------------\n");
            player.drawBoards();
        }
    }

    private Coordinate validateAndGetCoordinate(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (!input.startsWith("Fire ")) {
            throw new IllegalArgumentException("Invalid input");
        }

        input = input.replace("Fire ", "");

        if (input.length() < 2 || input.length() > 3) {
            throw new IllegalArgumentException("Invalid input");
        }

        char[] charArray = input.toUpperCase().toCharArray();
        int x = charArray[0] - 'A';
        final int y;
        if (charArray.length == 2) {
            y = Character.getNumericValue(charArray[1]);
        } else {
            y = Character.getNumericValue(charArray[1] + charArray[2]);
        }

        if (!(x >= 0 && x <= 9 && y >= 1 && y <= 10)) {
            throw new IllegalArgumentException("Invalid input");
        }

        return new Coordinate(x, y - 1);
    }
}
