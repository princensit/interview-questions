package com.prince.design.snake_ladder;

import java.util.Random;

/**
 * @author Prince Raj
 */
public class ScoreBoard {

    private static final int MIN_VALUE = 0;

    private static final int MAX_VALUE = 9;

    private static final int BOARD_SIZE = (MAX_VALUE + 1) * (MAX_VALUE + 1);

    private static final Coordinate START_COORDINATE = new Coordinate(MIN_VALUE, MIN_VALUE);

    private static final Coordinate END_COORDINATE = new Coordinate(MAX_VALUE, MAX_VALUE);

    private static final Block[][] BOARD = new Block[MAX_VALUE + 1][MAX_VALUE + 1];

    private static final Block[] ARRAY = new Block[(MAX_VALUE + 1) * (MAX_VALUE + 1)];

    private final Player[] players;

    private Player currentPlayer;

    private int currentIndex = 0;

    private final Random random = new Random();

    public ScoreBoard(int noOfPlayers) {
        players = initializePlayers(noOfPlayers);
        currentPlayer = players[currentIndex % players.length];
    }

    private Player[] initializePlayers(int noOfPlayers) {
        if (noOfPlayers < 1) {
            throw new RuntimeException("No sufficient players to play this game");
        }

        if (noOfPlayers > 4) {
            throw new RuntimeException("Max 4 players are supported to play this game");
        }

        Player[] players = new Player[noOfPlayers];

        Color[] colors = Color.values();

        for (int i = 0; i < BOARD_SIZE; i++) {
            Block block = new Block();
            block.setPosition(i);
        }

        // Block block = new Block();
        // block.setCoordinate(START_COORDINATE);

        for (int i = 0; i < noOfPlayers; i++) {
            Player player = new Player();
            player.setColor(colors[i]);
            player.setBlock(ARRAY[0]);

            players[i] = player;
        }

        return players;
    }

    private void play() {
        int value = getDiceValue();
        Block block = currentPlayer.getBlock();
        int position = block.getPosition();
        int newPosition = position + value;

        if (newPosition > BOARD_SIZE - 1) {
            System.out.println("No valid move. Moving to next user");
        }
    }

    // private Coordinate getNewBlock(Block block, int value) {
    // Coordinate coordinate = block.getCoordinate();
    // boolean moveRight = block.isMoveRight();
    //
    // int x = coordinate.getX();
    // int y = coordinate.getY();
    //
    // if (moveRight) {
    // int increaseX = 0;
    // int increaseY = 0;
    // if (x + value > MAX_VALUE) {
    // int extra = (x + value - MAX_VALUE);
    // increaseY += 1;
    // int newX = MAX_VALUE - extra;
    // } else {
    // increaseX = x + value;
    // }
    // } else {
    //
    // }
    // }

    private int getDiceValue() {
        return random.nextInt(6) + 1;
    }
}
