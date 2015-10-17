package com.base.figures;

import com.base.FigureBoard;
import com.base.Position;

import java.util.Collection;

/**
 * Queen functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class Queen extends LongDistanceFigure {
    private static final String NAME = "Q";

    public Queen(Position position) {
        super(position);
    }

    public Collection<Position> placeOnBoard(FigureBoard resultBoard) {
        Collection<Position> queenCoverage = fillLines(resultBoard);
        if(queenCoverage.isEmpty()){
            return EMPTY_RESULT;
        }

        Collection<Position> diagonalCoverage = fillDiagonals(resultBoard);
        if(diagonalCoverage.isEmpty()){
            return EMPTY_RESULT;
        }

        queenCoverage.addAll(diagonalCoverage);
        return queenCoverage;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
