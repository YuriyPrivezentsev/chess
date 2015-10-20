package com.base.figures;

import com.base.board.FigureBoard;
import com.base.board.Position;

import java.util.Collection;
import java.util.Collections;

/**
 * Abstract class for figure, which forces the common constructor and implement work with position.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public abstract class AbstractFigure implements Figure {
    private Position position;

    @SuppressWarnings("unchecked")
    protected static final Collection<Position> EMPTY_RESULT = Collections.EMPTY_LIST;

    public AbstractFigure(Position position) {
        this.position = position;
    }

    public AbstractFigure() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    protected boolean addCoverage(Collection<Position> coverage, FigureBoard resultBoard, int line, int column) {
        Position position = new Position(line, column, resultBoard);
        if (resultBoard.hasFigure(position)) {
            return false;
        }
        coverage.add(position);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Figure other) {
        return getPriority() - other.getPriority();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("StringEquality")
    @Override
    public boolean isSameType(Figure other) {
        /*Here is a little hack in speed: since we know that getName returns constant and constants are inlined
        * we can allow ourself a bit of non-strict behavior*/
        return other != null && getName() == other.getName();
    }
}
