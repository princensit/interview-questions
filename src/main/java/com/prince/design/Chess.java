package com.prince.design;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.mutable.MutableBoolean;

import lombok.Data;

/**
 * @author Prince Raj
 */
public class Chess {

    // TODO preserve histories to allow undo

    private static final int WIDTH = 8;

    private static final char BORDER_KNOT = '+';

    private static final char HORIZONTAL_BORDER = '-';

    private static final char VERTICAL_BORDER = '|';

    private int count = 0;

    public static void main(String[] args) {
        Chess chess = new Chess();
        Board board = chess.initializeBoard();

        // print current positions
        chess.printCurrentBoard(board);

        Player player1 = board.getPlayer1();
        Player player2 = board.getPlayer2();

        while (board.getWinner() == null) {
            chess.getNextBestMove(board, player1);
            chess.printCurrentBoard(board);

            chess.getNextBestMove(board, player2);
            chess.printCurrentBoard(board);

            break;
        }

        System.out.println("========> Winner: " + board.getWinner());
    }

    private void printCurrentBoard(Board board) {
        System.out.println("========> Step " + ++count);

        int maxWidth = getMaxWidth();
        printHorizontalBorder(maxWidth);

        Piece[][] matrix = board.getMatrix();
        for (Piece[] row : matrix) {
            StringBuilder builder = new StringBuilder(maxWidth * 8);
            builder.append(VERTICAL_BORDER);

            for (Piece piece : row) {
                String type = "";
                if (piece != null) {
                    type = piece.getType().name();

                    Color color = piece.getColor();
                    String info = color.name().substring(0, 1);
                    type += '(' + info + ')';
                }

                int length = type.length();
                builder.append(type);

                for (int i = 0; i < (maxWidth - length); i++) {
                    builder.append(' ');
                }
                builder.append(VERTICAL_BORDER);
            }
            System.out.println(builder);
            printHorizontalBorder(maxWidth);
        }

        System.out.println();
        System.out.println();
        System.out.println();
    }

    private Board initializeBoard() {
        Board board = new Board();
        Piece[][] matrix = board.getMatrix();

        Player player1 = new Player();
        player1.setColor(Color.WHITE);
        board.setPlayer1(player1);

        Player player2 = new Player();
        player2.setColor(Color.BLACK);
        board.setPlayer2(player2);

        Map<Player, Set<Piece>> playerPieces = board.getPlayerPieces();
        playerPieces.put(player1, new HashSet<Piece>());
        playerPieces.put(player2, new HashSet<Piece>());

        Map<Position, Piece> positionPieceMap = board.getPositionPieceMap();

        initializePlayer(matrix, 0, 1, player1, playerPieces.get(player1), positionPieceMap);
        initializePlayer(matrix, 7, 6, player2, playerPieces.get(player2), positionPieceMap);

        return board;
    }

    private boolean isValidPosition(
            Player player,
            Position newPos,
            Map<Position, Piece> positionPieceMap,
            MutableBoolean otherPlayerFoundInTempPos) {
        boolean valid = false;
        Piece existingPiece = positionPieceMap.get(newPos);
        if (existingPiece != null) {
            Color currentPlayerColor = player.getColor();
            Color existingPieceColor = existingPiece.getColor();

            if (!currentPlayerColor.equals(existingPieceColor)) {
                valid = true;
                otherPlayerFoundInTempPos.setValue(true);
            }
        } else {
            valid = true;
        }

        return valid;
    }

    // TODO more intelligence to be built
    private void getNextBestMove(Board board, Player player) {
        Piece[][] matrix = board.getMatrix();
        Map<Position, Piece> positionPieceMap = board.getPositionPieceMap();
        Map<Player, Set<Piece>> playerPieces = board.getPlayerPieces();
        Set<Piece> pieces = playerPieces.get(player);

        boolean foundNextPos = false;
        for (Piece piece : pieces) {
            if (piece.getType().equals(PieceType.PAWN)) {
                Position pos = piece.getPos();
                PieceType type = piece.getType();
                int maxMoves = type.getMaxMoves();

                int x = pos.getX();
                int y = pos.getY();

                List<Direction> directions = type.getDirections();
                for (Direction direction : directions) {
                    if (Direction.FORWARD.equals(direction)) {
                        for (int i = 1; i <= maxMoves; i++) {
                            int tempX;
                            if (Color.WHITE.equals(player.getColor())) {
                                tempX = x + i;
                            } else {
                                tempX = x - i;
                            }

                            Position tempPos = new Position();
                            tempPos.setX(tempX);
                            tempPos.setY(y);

                            MutableBoolean otherPlayerFoundInTempPos = new MutableBoolean(false);
                            boolean valid =
                                    isValidPosition(player, tempPos, positionPieceMap, otherPlayerFoundInTempPos);
                            if (valid) {
                                foundNextPos = true;

                                if (otherPlayerFoundInTempPos.isTrue()) {
                                    Piece otherPlayerPiece = positionPieceMap.get(tempPos);
                                    Color color = piece.getColor();

                                    Player otherPlayer = board.getPlayer1();
                                    if (!otherPlayer.getColor().equals(color)) {
                                        otherPlayer = board.getPlayer2();
                                    }

                                    Set<Piece> otherPlayerPieces = playerPieces.get(otherPlayer);
                                    otherPlayerPieces.remove(otherPlayerPiece);
                                    System.out.println("######## other player piece removed: " + otherPlayerPiece);
                                }

                                positionPieceMap.put(tempPos, piece);
                                positionPieceMap.remove(pos);
                                matrix[x][y] = null;
                                matrix[tempX][y] = piece;

                                break;
                            }
                        }
                    }

                    if (foundNextPos) {
                        break;
                    }
                }

                if (foundNextPos) {
                    break;
                }
            }
        }
    }

    private void initializePlayer(
            Piece[][] matrix,
            int row1,
            int row2,
            Player player,
            Set<Piece> playerPieces,
            Map<Position, Piece> positionPieceMap) {
        initializeOtherThanPawns(matrix, row1, player, playerPieces, positionPieceMap);
        initializePawns(matrix, row2, player, playerPieces, positionPieceMap);
    }

    private void initializeOtherThanPawns(
            Piece[][] matrix,
            int row,
            Player player,
            Set<Piece> playerPieces,
            Map<Position, Piece> positionPieceMap) {

        Color color = player.getColor();
        PieceType[] pieceTypes =
                {
                        PieceType.ROOK,
                        PieceType.KNIGHT,
                        PieceType.BISHOP,
                        PieceType.KING,
                        PieceType.QUEEN,
                        PieceType.BISHOP,
                        PieceType.KNIGHT,
                        PieceType.ROOK};

        int col = 0;
        for (PieceType type : pieceTypes) {
            Piece rookPiece1 = getPiece(color, row, col, type);
            initializeMatrixAndPlayersMap(matrix, rookPiece1, row, col, playerPieces, positionPieceMap);

            col++;
        }
    }

    private void initializePawns(
            Piece[][] matrix,
            int row,
            Player player,
            Set<Piece> playerPieces,
            Map<Position, Piece> positionPieceMap) {
        for (int j = 0; j < WIDTH; j++) {
            Piece pawnPiece = getPiece(player.getColor(), row, j, PieceType.PAWN);
            initializeMatrixAndPlayersMap(matrix, pawnPiece, row, j, playerPieces, positionPieceMap);
        }
    }

    private void initializeMatrixAndPlayersMap(
            Piece[][] matrix,
            Piece piece,
            int row,
            int col,
            Set<Piece> playerPieces,
            Map<Position, Piece> positionPieceMap) {
        matrix[row][col] = piece;
        playerPieces.add(piece);
        positionPieceMap.put(piece.getPos(), piece);
    }

    private Piece getPiece(Color color, int x, int y, PieceType type) {
        Piece piece = new Piece();
        piece.setType(type);
        piece.setColor(color);
        Position pos = new Position();
        pos.setX(x);
        pos.setY(y);
        piece.setPos(pos);

        return piece;
    }

    private void printHorizontalBorder(int maxWidth) {
        StringBuilder builder = new StringBuilder(maxWidth * 8);
        builder.append(BORDER_KNOT);
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < maxWidth; j++) {
                builder.append(HORIZONTAL_BORDER);
            }
            builder.append(BORDER_KNOT);
        }
        System.out.println(builder);
    }

    private int getMaxWidth() {
        PieceType[] pieceTypes = PieceType.values();

        int maxWidth = 0;
        for (PieceType type : pieceTypes) {
            int length = type.name().length();
            if (length > maxWidth) {
                maxWidth = length;
            }
        }

        return maxWidth + 3;
    }

    private enum Color {
        BLACK, WHITE
    }

    private enum Direction {
        LEFT, RIGHT, FORWARD, BACKWARD, DIAGONAL, KNIGHT_MOVE
    }

    private enum PieceType {
        PAWN(Collections.singletonList(Direction.FORWARD), 1), ROOK(Arrays.asList(
                Direction.LEFT,
                Direction.RIGHT,
                Direction.FORWARD,
                Direction.BACKWARD), 8), KNIGHT(Collections.singletonList(Direction.KNIGHT_MOVE), 3), BISHOP(
                Collections.singletonList(Direction.DIAGONAL),
                8), KING(Arrays.asList(
                Direction.LEFT,
                Direction.RIGHT,
                Direction.FORWARD,
                Direction.BACKWARD,
                Direction.DIAGONAL), 1), QUEEN(Arrays.asList(
                Direction.LEFT,
                Direction.RIGHT,
                Direction.FORWARD,
                Direction.BACKWARD,
                Direction.DIAGONAL), 8);

        private final List<Direction> directions;

        private final int maxMoves;

        PieceType(List<Direction> directions, int maxMoves) {
            this.directions = directions;
            this.maxMoves = maxMoves;
        }

        public List<Direction> getDirections() {
            return directions;
        }

        public int getMaxMoves() {
            return maxMoves;
        }
    }

    @Data
    private class Board {

        private Piece[][] matrix = new Piece[8][8];

        private Player winner;

        private Player player1;

        private Player player2;

        private Map<Player, Set<Piece>> playerPieces = new HashMap<>();

        private Map<Position, Piece> positionPieceMap = new HashMap<>();
    }

    @Data
    private class Piece {

        private PieceType type;

        private Color color;

        private Position pos;
    }

    @Data
    private class Position {

        private int x;

        private int y;
    }

    @Data
    private class Player {

        private Color color;
    }
}
