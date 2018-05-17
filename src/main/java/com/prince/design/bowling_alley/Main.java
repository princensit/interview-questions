package com.prince.design.bowling_alley;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Prince Raj
 */
public class Main {

    // Alley
    // - no of pins
    // - scored pins
    // - spare
    // - strike
    //
    // Bowl
    // - score
    //
    // Player
    // - Name
    // - firstBowl
    // - secondBowl
    // - thirdBowl (in case strike or spare in last set)
    // - bowlArray
    //
    // ScoreBoard
    // - MAX_PINS
    // - TOTAL_SETS
    // - List of players
    // - Map<Player, Score>
    // - winner
    //
    // ScoreStrategy
    // - Strike bonus
    // - Spare bonus
    //
    // Main
    // - No of sets
    // - No of lanes
    //
    // Lane
    // - ScoreBoard
    // -

    public static void main(String[] args) throws Exception {
        int noOfSets = 10;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int players = Integer.parseInt(br.readLine());

        ScoreBoard scoreBoard = new ScoreBoard(noOfSets, players);

        boolean cont = true;
        while (cont) {
            String score = br.readLine();
            cont = scoreBoard.play(score);
        }

        System.out.println("Winner -> " + scoreBoard.getWinner());
    }
}
