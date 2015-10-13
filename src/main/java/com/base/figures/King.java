package com.base.figures;

import com.base.BoardOperations;
import com.base.Position;

/**
 * King functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class King extends AbstractFigure {
    private static final String NAME = "K";

    public King(Position position) {
        super(position);
    }

    public King(int x, int y) {
        super(x, y);
    }

    public void fillInBoard(int[][] board) {
        int xStart = getPosition().getLine() != 0 ? getPosition().getLine() - 1 : 0;
        int xFinish = getPosition().getLine() < board.length - 1 ? getPosition().getLine() + 1 : board.length - 1;
        int yStart = getPosition().getColumn() != 0 ? getPosition().getColumn() - 1 : 0;
        int yFinish = getPosition().getColumn() < board.length - 1 ? getPosition().getColumn() + 1 : board.length - 1;
        for(int i = xStart; i <= xFinish; i++ ){
            for (int j = yStart; j <= yFinish; j++){
                board[i][j] = BoardOperations.TAKEN;
            }
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
