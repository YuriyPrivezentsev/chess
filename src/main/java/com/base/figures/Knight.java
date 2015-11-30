package com.base.figures;

import com.base.board.FigureBoard;
import com.base.board.Position;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Knight functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class Knight extends AbstractFigure {
    private static final Type TYPE = Type.KNIGHT;
    public static final int KNIGHT_COVERAGE_CAPACITY = 9;
    private static final int PRIORITY = 4;

    public Knight(Position position) {
        super(position);
    }

    public Knight() {

    }

    /**
     *  {@inheritDoc}
     * @param resultBoard
     */
    @Override
    public Collection<Position> placeOnBoard(FigureBoard resultBoard) {
        Collection<Position> knightCoverage = new ArrayList<>(KNIGHT_COVERAGE_CAPACITY);
        knightCoverage.add(getPosition());

        int counter = 0;
        int yStart = getPosition().getLine() - 2;
        int yFinish =  getPosition().getLine() + 3;
        int xStart = getPosition().getColumn() - 2;
        int xFinish = getPosition().getColumn() + 3;
        for (int line = yStart; line < yFinish; line++) {
            for (int column = xStart; column < xFinish; column++) {
                if (line >= 0 && line != getPosition().getLine() && line < resultBoard.getHeight()
                        && column >= 0 && column != getPosition().getColumn() && column < resultBoard.getWidth()
                        && counter % 2 == 1) {
                    if (!addCoverage(knightCoverage, resultBoard, line, column)) {
                        return EMPTY_RESULT;
                    }
                }
                counter++;
            }
        }
        return knightCoverage;
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
        return new Knight(getPosition());
    }
}
