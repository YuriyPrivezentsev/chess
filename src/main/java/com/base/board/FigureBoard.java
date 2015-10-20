package com.base.board;

import com.base.figures.Figure;

/**
 * Represents the board with already place figures .
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public interface FigureBoard extends Board {

    /**
     * Checks whether any of existing figures is already placed on the cell.
     */
    boolean hasFigure(Position underAttack);

    /**
     * Place figure on the result board.
     */
    void addFigure(Figure figure);

    /**
     * Removes figure from the result board.
     */
    void removeFigure(Figure deleteFigure);
}
