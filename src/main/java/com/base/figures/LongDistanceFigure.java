package com.base.figures;

import com.base.board.FigureBoard;
import com.base.board.Position;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Parent class for figures, which can go across all board.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public abstract class LongDistanceFigure extends AbstractFigure {

    public LongDistanceFigure(Position position) {
        super(position);
    }

    public LongDistanceFigure() {

    }

    /**
     * Calculate coverage for strait lines figures, like Queen and Rook.
     *
     * @param resultBoard - the board with figures placed
     * @return - The collection of positions which can be attacked by the figure, including its own place
     */
    protected Collection<Position> fillLines(FigureBoard resultBoard) {
        int height = resultBoard.getHeight();
        int width = resultBoard.getWidth();
        Collection<Position> lineCoverage = new ArrayList<>(height + width);

        for (int line = 0; line < height; line++) {
            if (!addCoverage(lineCoverage, resultBoard, line, getPosition().getColumn())) {
                return EMPTY_RESULT;
            }
        }

        for (int column = 0; column < width; column++) {
            if (!addCoverage(lineCoverage, resultBoard, getPosition().getLine(), column)) {
                return EMPTY_RESULT;
            }
        }

        return lineCoverage;
    }

    /**
     * Calculate coverage for diagonal lines figures, like Queen and Bishop.
     *
     * @param resultBoard - the board with figures placed
     * @return - The collection of positions which can be attacked by the figure, including its own place
     */
    protected Collection<Position> fillDiagonals(FigureBoard resultBoard) {

        int height = resultBoard.getHeight();
        int width = resultBoard.getWidth();

        int diagonalLengthEstimate = (height + width) * 2;
        Collection<Position> diagonalCoverage = new ArrayList<>(diagonalLengthEstimate);

        for (int line = getPosition().getLine(), column = getPosition().getColumn();
             line < height && column < width;
             line++, column++) {
            if (!addCoverage(diagonalCoverage, resultBoard, line, column)) {
                return EMPTY_RESULT;
            }
        }

        for (int line = getPosition().getLine() - 1, column = getPosition().getColumn() - 1;
             line >= 0 && column >= 0;
             line--, column--) {
            if (!addCoverage(diagonalCoverage, resultBoard, line, column)) {
                return EMPTY_RESULT;
            }
        }

        for (int line = getPosition().getLine() + 1, column = getPosition().getColumn() - 1;
             line < height && column >= 0;
             line++, column--) {
            if (!addCoverage(diagonalCoverage, resultBoard, line, column)) {
                return EMPTY_RESULT;
            }
        }

        for (int line = getPosition().getLine() - 1, column = getPosition().getColumn() + 1;
             line >= 0 && column < width;
             line--, column++) {
            if (!addCoverage(diagonalCoverage, resultBoard, line, column)) {
                return EMPTY_RESULT;
            }
        }

        return diagonalCoverage;
    }

}