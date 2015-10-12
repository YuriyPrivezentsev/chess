package com.base.figures;

import com.base.Position;

/**
 * Bishop functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class Bishop  extends LongDistanceFigure {
    public Bishop(Position position) {
        super(position);
    }

    public Bishop(int x, int y) {
        super(x, y);
    }

    public void fillInBoard(int[][] board) {
        fillDiagonals(board);
    }
}
