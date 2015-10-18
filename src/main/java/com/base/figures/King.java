package com.base.figures;

import com.base.board.FigureBoard;
import com.base.board.Position;

import java.util.ArrayList;
import java.util.Collection;

/**
 * King functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class King extends AbstractFigure {
    public static final String NAME = "K";
    public static final int KING_COVERAGE_CAPACITY = 9;
    private static final int PRIORITY = 5;

    public King(Position position) {
        super(position);
    }

    public King() {
        super();
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public Collection<Position> placeOnBoard(FigureBoard resultBoard) {
        Collection<Position> kingCoverage = new ArrayList<>(KING_COVERAGE_CAPACITY);
        int yStart = getPosition().getLine() != 0 ? getPosition().getLine() - 1 : 0;
        int yFinish = getPosition().getLine() < resultBoard.getHeight() - 1
                ? getPosition().getLine() + 1
                : resultBoard.getHeight() - 1;
        int xStart = getPosition().getColumn() != 0 ? getPosition().getColumn() - 1 : 0;
        int xFinish = getPosition().getColumn() < resultBoard.getWidth() - 1
                ? getPosition().getColumn() + 1
                : resultBoard.getWidth() - 1;

        for (int line = yStart; line <= yFinish; line++) {
            for (int column = xStart; column <= xFinish; column++) {
                if (!addCoverage(kingCoverage, resultBoard, line, column)) {
                    return EMPTY_RESULT;
                }

            }
        }
        return kingCoverage;
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
}
