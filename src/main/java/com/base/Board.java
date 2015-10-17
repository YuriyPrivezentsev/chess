package com.base;

/**
 * Represents the game board.
 *
 * @author Yuriy Privezentsev
 * @since 10/17/2015
 */
public interface Board {
    String FREE_POSITION_MARKER = "-";

    int getWidth();
    int getHeight();
}
