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
    private static final String NAME = "B";

    public Knight(Position position) {
        super(position);
    }

    public Knight(int x, int y) {
        super(x, y);
    }

    public void fillInBoard(int[][] board) {
        board[getPosition().getLine()][getPosition().getColumn()] = BoardOperations.TAKEN;
        int counter = 0;
        for (int i = getPosition().getLine() - 2; i < Math.min(board.length, getPosition().getLine() + 3); i++) {
            for (int j = getPosition().getColumn() - 2; j < Math.min(board.length, getPosition().getColumn() + 3); j++) {
                if (i >= 0 && i != getPosition().getLine()
                        && j > 0 && j != getPosition().getColumn()
                        && counter % 2 == 1) {
                    board[i][j] = BoardOperations.TAKEN;
                }
                counter++;
            }
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
