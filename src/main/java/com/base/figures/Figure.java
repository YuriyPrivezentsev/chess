package com.base.figures;

import com.base.Position;

/**
 * Interface representing chess figure.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public interface Figure {
    /**
     * Mark all the fields which are threatened by this figure, including the figure position.
     * @param board — the board to be marked
     */
    void fillInBoard(int[][] board);

    /**
     * Current figure position.
     */
    Position getPosition();
}
