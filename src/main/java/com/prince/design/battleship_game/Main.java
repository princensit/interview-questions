package com.prince.design.battleship_game;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Game game = Game.getInstance();
        game.initialize();

        while (!game.winnerFound()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();

            if ("Draw Board".equals(input)) {
                game.drawGame();
            } else {
                try {
                    game.play(input);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage() + ", you can retry");
                }
            }
        }

        System.out.println("Winner player name: " + game.getWinnerPlayer());
    }
}
