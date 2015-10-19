package com.base.figures;

import com.base.board.FigureBoard;
import com.base.board.Position;

import java.util.Collection;

/**
 * Rook functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class Rook extends LongDistanceFigure {
    public static final String NAME = "R";
    private static final int PRIORITY = 1;

    public Rook(Position position) {
        super(position);
    }

    public Rook() {

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
     * @param resultBoard
     */
    @Override
    public Collection<Position> placeOnBoard(FigureBoard resultBoard) {
        return fillLines(resultBoard);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String getName() {
        return NAME;
    }
}
