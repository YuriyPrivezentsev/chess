package com.base.figures;

import com.base.BoardOperations;
import com.base.Position;

/**
 * Parent class for figures, which can go across all board.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public abstract class LongDistanceFigure  extends AbstractFigure {

    public LongDistanceFigure(Position position) {
        super(position);
    }


    public LongDistanceFigure(int x, int y) {
        super(x, y);
    }

    protected void fillLines(int[][] board) {
        int size = board.length;
        for (int i = 0; i < size; i++) {
            board[i][getPosition().getY()] = BoardOperations.MARKER;
            board[getPosition().getX()][i] = BoardOperations.MARKER;
        }
    }

    protected void fillDiagonals(int[][] board) {
        int size = board.length;
        for (int i = getPosition().getX(), j = getPosition().getY(); i < size && j < size; i++, j++) {
            board[i][j] = BoardOperations.MARKER;
        }

        for (int i = getPosition().getX() - 1, j = getPosition().getY() - 1; i >= 0 && j >= 0; i--, j--) {
            board[i][j] = BoardOperations.MARKER;
        }

        for (int i = getPosition().getX() + 1, j = getPosition().getY() - 1; i < size && j >= 0; i++, j--) {
            board[i][j] = BoardOperations.MARKER;
        }

        for (int i = getPosition().getX() - 1, j = getPosition().getY() + 1; i >= 0 && j < size; i--, j++) {
            board[i][j] = BoardOperations.MARKER;
        }
    }

}