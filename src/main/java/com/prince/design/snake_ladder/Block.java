package com.prince.design.snake_ladder;

import lombok.Data;

/**
 * @author Prince Raj
 */
@Data
public class Block {

    private int position;

    private Coordinate coordinate;

    private Type type;

    private boolean moveRight;
}
