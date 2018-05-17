package com.prince.design.bowling_alley;

import java.util.List;

import com.prince.design.bowling_alley.exceptions.InvalidInputException;

/**
 * @author Prince Raj
 */
public class ScoreStrategy {

    public static final int TOTAL_PINS = 10;

    public static final int SPARE_BONUS = 5;

    public static final int STRIKE_BONUS = 10;

    public static final String SPARE = "/";

    public static final String STRIKE = "X";

    private int count = 0;

    private int lastSetCount = 0;

    private boolean isStrikeSpare = false;

    public boolean score(Player player, String score, boolean lastSet) {
        List<String> alleyScore = player.getAlleyScore();

        boolean nextChance = true;
        int scoreInt = 0;
        try {
            scoreInt = Integer.parseInt(score);
            if (lastSet) {
                lastSetCount++;
            } else {
                count++;
            }
            if (count == 2) {
                int oldScore = Integer.parseInt(alleyScore.get(alleyScore.size() - 1));
                if (oldScore + scoreInt >= TOTAL_PINS) {
                    throw new InvalidInputException("Invalid input");
                }
            }
        } catch (NumberFormatException e) {
            count++;
            // if (count == 2) {
            // if (STRIKE.equals(score)) {
            // throw new InvalidInputException("Wrong input thrown");
            // }
            // }

            if (STRIKE.equals(score)) {
                scoreInt += TOTAL_PINS + STRIKE_BONUS;
                if (lastSet) {
                    isStrikeSpare = true;
                    lastSetCount++;
                    nextChance = true;
                } else {
                    nextChance = false;
                }
            } else if (SPARE.equals(score)) {
                int oldScore = Integer.parseInt(alleyScore.get(alleyScore.size() - 1));
                scoreInt += TOTAL_PINS - oldScore + SPARE_BONUS;
                if (lastSet) {
                    isStrikeSpare = true;
                    lastSetCount++;
                    nextChance = true;
                } else {
                    nextChance = false;
                }
            }
        }

        alleyScore.add(score);

        player.addScore(scoreInt);

        if (count == 2 || (isStrikeSpare && lastSetCount == 3) || (lastSetCount == 2)) {
            nextChance = false;
            count = 0;
            lastSetCount = 0;
        }

        return nextChance;
    }
}
