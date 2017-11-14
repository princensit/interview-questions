package com.prince.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Prince Raj
 */
public class SnakeLadder {

    private static final int MAX_NUMBER_IN_DICE = 6;

    private static final int BOARD_SIZE = 20;

    private Player winner;

    private final Map<Integer, Integer> ladderMap;

    private final Map<Integer, Integer> snakeMap;

    private SortedMap<Player, Integer> playerPositionMap = new TreeMap<>();

    private final Random random = new Random();

    private SnakeLadder(Player[] players, Map<Integer, Integer> ladderMap, Map<Integer, Integer> snakeMap) {
        this.ladderMap = ladderMap;
        this.snakeMap = snakeMap;

        for (Player player : players) {
            playerPositionMap.put(player, 1);
        }
    }

    public static void main(String[] args) throws Exception {
        Player[] players = Player.values();
        Map<Integer, Integer> ladderMap = getLadderMap();
        Map<Integer, Integer> snakeMap = getSnakeMap();

        SnakeLadder game = new SnakeLadder(players, ladderMap, snakeMap);

        game.printPlayerPositions();

        Player winner;
        while ((winner = game.getWinner()) == null) {
            for (Player player : players) {
                game.play(player);

                game.printPlayerPositions();

                Thread.sleep(1000);
            }
        }

        System.out.println("Winner: " + winner);
    }

    private void play(Player player) {
        int moves = random.nextInt(MAX_NUMBER_IN_DICE) + 1;
        System.out.println("Random number in dice: " + moves);

        int currentPos = playerPositionMap.get(player);
        int newPos = currentPos + moves;
        Integer tempNewPos = ladderMap.get(newPos);
        if (tempNewPos == null) {
            tempNewPos = snakeMap.get(newPos);
        }

        if (tempNewPos != null) {
            newPos = tempNewPos;
        }

        if (newPos <= BOARD_SIZE) {
            playerPositionMap.put(player, newPos);
        }

        if (newPos == BOARD_SIZE) {
            winner = player;
        }
    }

    private Player getWinner() {
        return winner;
    }

    private void printPlayerPositions() {
        System.out.println(playerPositionMap);
    }

    private static Map<Integer, Integer> getLadderMap() {
        Map<Integer, Integer> ladderMap = new HashMap<>();
        ladderMap.put(3, 6);
        ladderMap.put(7, 10);
        ladderMap.put(15, 35);
        ladderMap.put(17, 25);
        ladderMap.put(22, 40);
        ladderMap.put(50, 70);
        ladderMap.put(60, 90);

        return ladderMap;
    }

    private static Map<Integer, Integer> getSnakeMap() {
        Map<Integer, Integer> snakeMap = new HashMap<>();
        snakeMap.put(12, 4);
        snakeMap.put(18, 6);
        snakeMap.put(30, 11);
        snakeMap.put(52, 31);
        snakeMap.put(77, 66);

        return snakeMap;
    }

    private enum Player {
        RED, GREEN, BLUE, YELLOW
    }
}
