package com.prince.design.bowling_alley;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Prince Raj
 */
public class Player {

    private final String name;

    private List<String> alleyScore = new ArrayList<>();

    private int score;

    public Player(String name) {
        this.name = name;
    }

    public boolean score(ScoreStrategy scoreStrategy, String score, boolean lastSet) {
        return scoreStrategy.score(this, score, lastSet);
    }

    public List<String> getAlleyScore() {
        return alleyScore;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    @Override
    public String toString() {
        return name + ':' + alleyScore + " -> " + score;
    }
}
