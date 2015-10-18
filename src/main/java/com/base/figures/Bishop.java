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

    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Collection<Position> placeOnBoard(FigureBoard resultBoard) {
        return fillDiagonals(resultBoard);
    }
}
