package com.prince.design;

import lombok.Data;

/**
 * @author Prince Raj
 */
public class Othello {

}

class Game {

    private static final int ROWS = 10;

    private static final int COLUMNS = 10;

    private static Game instance;

    private Board board;

    private Player[] players;

    private Game() {
        board = new Board(ROWS, COLUMNS);
        players = new Player[2];
        players[0] = new Player(Color.BLACK);
        players[1] = new Player(Color.WHITE);
    }

    public static Game getInstance() {
        if (instance == null) {
            synchronized (Game.class) {
                if (instance == null) {
                    instance = new Game();
                }
            }
        }

        return instance;
    }

    public Board getBoard() {
        return board;
    }
}

@Data
class Board {

    private int blackCount = 0;

    private int whiteCount = 0;

    private OthelloPiece[][] board;

    public Board(int rows, int columns) {
        this.board = new OthelloPiece[rows][columns];

        initialize();
    }

    // attempt to place color at particular coordinate, returns true if successful
    public boolean placeColor(int r, int c, Color color) {
        return true;
    }

    // returns no of flipped items
    private int flipSection(int r, int c, Color color, Direction direction) {
        // TODO Implement me!

        return 0;
    }

    public int getScoreForColor(Color color) {
        if (Color.BLACK == color) {
            return blackCount;
        } else {
            return whiteCount;
        }
    }

    // update board with additional newPieces pieces of color newColor. Decrease score of opposite color.
    public void updateScore(Color newColor, int newPieces) {
        if (Color.BLACK == newColor) {
            blackCount += newPieces;
            whiteCount -= newPieces;
        } else {
            blackCount -= newPieces;
            whiteCount += newPieces;
        }
    }

    private void initialize() {
        int rows = board.length;
        int columns = board[0].length;

        board[rows / 2][columns] = new OthelloPiece(Color.BLACK);
        board[rows + 1][columns + 1] = new OthelloPiece(Color.BLACK);
        board[rows][columns + 1] = new OthelloPiece(Color.WHITE);
        board[rows + 1][columns] = new OthelloPiece(Color.WHITE);
    }
}

class OthelloPiece {

    private Color color;

    public OthelloPiece(Color color) {
        this.color = color;
    }

    public void flip() {
        if (Color.BLACK == color) {
            color = Color.WHITE;
        } else {
            color = Color.BLACK;
        }
    }

    public Color getColor() {
        return color;
    }
}

// The Player holds only a very limited amount of information. It does not even hold its own score, but it does have a
// method one can call to get the score Player.getScore() will call out to the GameManager to retrieve this value.
class Player {

    private Color color;

    public Player(Color color) {
        this.color = color;
    }

    public int getScore() {
        return Game.getInstance().getBoard().getScoreForColor(color);
    }

    public boolean playPiece(int r, int c) {
        return Game.getInstance().getBoard().placeColor(r, c, color);
    }

    public Color getColor() {
        return color;
    }
}

enum Color {
    BLACK, WHITE
}

enum Direction {
    UP, DOWN, LEFT, RIGHT
}