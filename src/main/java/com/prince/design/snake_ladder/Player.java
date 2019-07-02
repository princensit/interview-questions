package com.prince.design.snake_ladder;

import lombok.Data;

/**
 * @author Prince Raj
 */
@Data
public class Player {

    private Color color;

    private Block block;

    private boolean winner;

}
