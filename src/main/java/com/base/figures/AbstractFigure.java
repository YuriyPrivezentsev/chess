package com.base.figures;

import com.base.FigureBoard;
import com.base.Position;

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

    public Position getPosition() {
        return position;
    }

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

    @Override
    public int compareTo(Figure other) {
        return getPriority() - other.getPriority();
    }
}
