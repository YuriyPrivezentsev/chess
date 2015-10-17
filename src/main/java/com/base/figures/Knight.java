package com.base.figures;

import com.base.FigureBoard;
import com.base.Position;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Knight functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class Knight extends AbstractFigure {
    private static final String NAME = "N";
    public static final int KNIGHT_COVERAGE_CAPACITY = 9;

    public Knight(Position position) {
        super(position);
    }

    public Collection<Position> placeOnBoard(FigureBoard resultBoard) {
        Collection<Position> knightCoverage = new ArrayList<>(KNIGHT_COVERAGE_CAPACITY);
        knightCoverage.add(getPosition());

        int counter = 0;
        int yStart = getPosition().getLine() - 2;
        int yFinish = Math.min(resultBoard.getHeight(), getPosition().getLine() + 3);
        int xStart = getPosition().getColumn() - 2;
        int xFinish = Math.min(resultBoard.getWidth(), getPosition().getColumn() + 3);

        for (int line = yStart; line < yFinish; line++) {
            for (int column = xStart; column < xFinish; column++) {
                if (line >= 0 && line != getPosition().getLine()
                        && column > 0 && column != getPosition().getColumn()
                        && counter % 2 == 1) {
                    if (!addCoverage(knightCoverage, resultBoard, line, column)){
                        return EMPTY_RESULT;
                    }
                }
                counter++;
            }
        }
        return knightCoverage;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
