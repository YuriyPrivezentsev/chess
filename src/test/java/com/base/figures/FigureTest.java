package com.base.figures;

import static org.junit.Assert.assertEquals;
import com.base.BoardOperations;

/**
 * Common figure test functionality
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public abstract class FigureTest {
    protected void performTest(Figure queen, int[][] checkBoard, int[][] board) {
        queen.fillInBoard(board);
        BoardOperations.printBoard(board);
        for (int i = 0; i < board.length; i++ ) {
            for (int j = 0 ; j < board.length; j ++){
                assertEquals(checkBoard[i][j], board[i][j]);
            }
        }
    }
}
