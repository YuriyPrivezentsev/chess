package com.base.figures;

import com.base.Position;

/**
 * Rook functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class Rook extends LongDistanceFigure {
    private static final String NAME = "R";

    public Rook(Position position) {
        super(position);
    }

    public Rook(int x, int y) {
        super(x, y);
    }

    public void fillInBoard(int[][] board) {
        fillLines(board);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
