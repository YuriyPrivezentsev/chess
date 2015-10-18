package com.base.board;

/**
 * The base board implementation with the common functional of retrieving common board characteristics, like dimensions.
 *
 * @author Yuriy Privezentsev
 * @since 10/17/2015
 */
public abstract class AbstractBoard implements Board {
    private final int width;
    private final int height;

    public AbstractBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
