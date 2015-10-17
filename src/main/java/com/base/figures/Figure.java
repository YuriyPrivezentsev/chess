package com.base.figures;

import com.base.FigureBoard;
import com.base.Position;

import java.util.Collection;

/**
 * Interface representing chess figure.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public interface Figure {
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
     * Gets the letter mark for the figure
     */
    String getName();
}
