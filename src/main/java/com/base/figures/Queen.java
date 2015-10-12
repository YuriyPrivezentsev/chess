package com.base.figures;

import com.base.Position;

/**
 * Queen functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class Queen extends LongDistanceFigure {

    public Queen(Position position) {
        super(position);
    }

    public Queen(int x, int y) {
        super(x, y);
    }

    public void fillInBoard(int[][] board) {
        fillDiagonals(board);
        fillLines(board);
    }
}