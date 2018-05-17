package com.prince.design.bowling_alley;

/**
 * @author Prince Raj
 */
public class ScoreBoard {


    private static final String PLAYER_NAME_PREFIX = "P";

    private int currentSet;

    private final Player[] players;

    // TODO to support multiple lane
    // private final Map<LaneInfo, List<Player>> lanePlayersMap;
    // private final Map<LaneInfo, ScoreStrategy> laneScoreStrategyMap;

    private final int totalSets;

    private int currentPlayer = 0;

    private Player winner;

    private ScoreStrategy scoreStrategy = new ScoreStrategy();

    public ScoreBoard(int noOfSets, int playersCount) {
        this.players = getPlayers(playersCount);
        this.totalSets = noOfSets;
    }

    public boolean play(String score) {
        boolean cont = true;

        if (currentPlayer == 0) {
            currentSet++;
        }

        Player player = players[currentPlayer];
        boolean isLastSet = isLastSet();
        boolean nextChance = scoreStrategy.score(player, score, isLastSet);

        printScore();

        if (isLastSet && currentPlayer == players.length - 1 && !nextChance) {
            cont = false;
        }

        if (!nextChance) {
            currentPlayer = (currentPlayer + 1) % players.length;
        }

        return cont;
    }

    private boolean isLastSet() {
        return currentSet == totalSets;
    }

    private Player[] getPlayers(int playersCount) {
        Player[] players = new Player[playersCount];

        for (int i = 0; i < playersCount; i++) {
            String playerName = PLAYER_NAME_PREFIX + (i + 1);
            players[i] = new Player(playerName);
        }

        return players;
    }

    public Player getWinner() {
        int max = 0;
        for (Player player : players) {
            int totalScore = player.getScore();
            if (totalScore > max) {
                winner = player;
            }
        }

        return winner;
    }

    public void printScore() {
        for (Player player : players) {
            System.out.println(player);
        }
    }
}
