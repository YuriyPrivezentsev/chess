package com.base.figures;

import com.base.BoardOperations;
import com.base.Position;

/**
 * Knight functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class Knight extends AbstractFigure {
    public Knight(Position position) {
        super(position);
    }

    public Knight(int x, int y) {
        super(x, y);
    }

    public void fillInBoard(int[][] board) {
        board[getPosition().getX()][getPosition().getY()] = BoardOperations.MARKER;
        int counter = 0;
        for (int i = getPosition().getX() - 2; i < Math.min(board.length, getPosition().getX() + 3); i++) {
            for (int j = getPosition().getY() - 2; j < Math.min(board.length, getPosition().getY() + 3); j++) {
                if (i >= 0 && i != getPosition().getX()
                        && j > 0 && j != getPosition().getY()
                        && counter % 2 == 1) {
                    board[i][j] = BoardOperations.MARKER;
                }
                counter++;
            }
        }
    }
}
