package com.base.figures;

import com.base.Position;

/**
 * Rook functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class Rook extends LongDistanceFigure {
    public Rook(Position position) {
        super(position);
    }

    public Rook(int x, int y) {
        super(x, y);
    }

    public void fillInBoard(int[][] board) {
        fillLines(board);
    }
}
