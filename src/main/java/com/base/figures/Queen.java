package com.base.figures;

import com.base.board.FigureBoard;
import com.base.board.Position;

import java.util.Collection;

/**
 * Queen functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class Queen extends LongDistanceFigure {
    private static final Type TYPE = Type.QUEEN;
    private static final int PRIORITY = 3;

    public Queen(Position position) {
        super(position);
    }

    public Queen() {
        super();
    }

    /**
     *  {@inheritDoc}
     * @param resultBoard
     */
    @Override
    public Collection<Position> placeOnBoard(FigureBoard resultBoard) {
        Collection<Position> queenCoverage = fillLines(resultBoard);
        if (queenCoverage.isEmpty()) {
            return EMPTY_RESULT;
        }

        Collection<Position> diagonalCoverage = fillDiagonals(resultBoard);
        if (diagonalCoverage.isEmpty()) {
            return EMPTY_RESULT;
        }

        queenCoverage.addAll(diagonalCoverage);
        return queenCoverage;
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
    public Type getType() {
        return TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Figure deepCopy() {
        return new Queen(getPosition());
    }
}
