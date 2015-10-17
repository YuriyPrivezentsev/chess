package com.base.figures;

import com.base.Position;

/**
 * Abstract class for figure, which forces the common constructor and implement work with position.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public abstract class AbstractFigure implements Figure{
    private final Position position;

    public AbstractFigure(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
