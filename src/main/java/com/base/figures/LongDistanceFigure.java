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

    protected void fillLines(int[][] board) {
        int size = board.length;
        for (int i = 0; i < size; i++) {
            board[i][getPosition().getColumn()] = BoardOperations.TAKEN;
            board[getPosition().getLine()][i] = BoardOperations.TAKEN;
        }
    }

    protected void fillDiagonals(int[][] board) {
        int size = board.length;
        for (int i = getPosition().getLine(), j = getPosition().getColumn(); i < size && j < size; i++, j++) {
            board[i][j] = BoardOperations.TAKEN;
        }

        for (int i = getPosition().getLine() - 1, j = getPosition().getColumn() - 1; i >= 0 && j >= 0; i--, j--) {
            board[i][j] = BoardOperations.TAKEN;
        }

        for (int i = getPosition().getLine() + 1, j = getPosition().getColumn() - 1; i < size && j >= 0; i++, j--) {
            board[i][j] = BoardOperations.TAKEN;
        }

        for (int i = getPosition().getLine() - 1, j = getPosition().getColumn() + 1; i >= 0 && j < size; i--, j++) {
            board[i][j] = BoardOperations.TAKEN;
        }
    }

}