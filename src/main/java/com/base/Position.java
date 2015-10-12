package com.base;

/**
 * Figure position on the board.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class Position {
    private final int x; //line
    private final int y; //column

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
