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

    public King(Position position) {
        super(position);
    }

    public King(int x, int y) {
        super(x, y);
    }

    public void fillInBoard(int[][] board) {
        int xStart = getPosition().getX() != 0 ? getPosition().getX() - 1 : 0;
        int xFinish = getPosition().getX() < board.length - 1 ? getPosition().getX() + 1 : board.length - 1;
        int yStart = getPosition().getY() != 0 ? getPosition().getY() - 1 : 0;
        int yFinish = getPosition().getY() < board.length - 1 ? getPosition().getY() + 1 : board.length - 1;
        for(int i = xStart; i <= xFinish; i++ ){
            for (int j = yStart; j <= yFinish; j++){
                board[i][j] = BoardOperations.MARKER;
            }
        }
    }
}
