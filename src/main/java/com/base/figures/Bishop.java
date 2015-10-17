package com.base.figures;

import com.base.FigureBoard;
import com.base.Position;

import java.util.Collection;
import java.util.Collections;

/**
 * Bishop functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class Bishop  extends LongDistanceFigure {
    private static final String NAME = "B";

    public Bishop(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Collection<Position> placeOnBoard(FigureBoard resultBoard) {
        return fillDiagonals(resultBoard);
    }
}
