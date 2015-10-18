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
    public static final String NAME = "Q";
    private static final int PRIORITY = 3;

    public Queen(Position position) {
        super(position);
    }

    public Queen() {
        super();
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

    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
