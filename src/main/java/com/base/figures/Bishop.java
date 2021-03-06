package com.base.figures;

import com.base.board.FigureBoard;
import com.base.board.Position;

import java.util.Collection;

/**
 * Bishop functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class Bishop extends LongDistanceFigure {
    public static final String NAME = "B";
    private static final int PRIORITY = 2;

    public Bishop(Position position) {
        super(position);
    }

    public Bishop() {

    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public int getPriority() {
        return PRIORITY;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public Collection<Position> placeOnBoard(FigureBoard resultBoard) {
        return fillDiagonals(resultBoard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Figure deepCopy() {
        return new Bishop(getPosition());
    }
}
