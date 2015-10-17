package com.base.figures;

import com.base.FigureBoard;
import com.base.Position;

import java.util.Collection;

/**
 * Rook functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class Rook extends LongDistanceFigure {
    private static final String NAME = "R";

    public Rook(Position position) {
        super(position);
    }

    public Collection<Position> placeOnBoard(FigureBoard resultBoard) {
        return fillLines(resultBoard);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
