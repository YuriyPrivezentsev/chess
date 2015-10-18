package com.base.figures;

import com.base.board.FigureBoard;
import com.base.board.Position;

import java.util.Collection;

/**
 * Interface representing chess figure.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public interface Figure extends Comparable<Figure> {
    /**
     * Try to position figure on board making sure it neither can be taken nor can take other figures on board.
     *
     * @param resultBoard - the board with figures placed
     * @return - The collection of positions which can be attacked by the figure, including its own place
     */
    Collection<Position> placeOnBoard(FigureBoard resultBoard);

    /**
     * Current figure position.
     */
    Position getPosition();

    /**
     * Set current figure position on board
     */
    void setPosition(Position position);

    /**
     * Get the priority in which to process the figures.
     * The priorities are chosen based on the combination of
     * complexity of figure processing and number of cells occupied on board.
     */
    int getPriority();

    /**
     * Gets the letter mark for the figure
     */
    String getName();

    /**
     * Test whether the current figure is of the same class as the other one.
     */
    boolean isSameType(Figure other);
}
