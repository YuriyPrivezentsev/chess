package com.base.figures;

import static org.junit.Assert.assertEquals;

import com.base.Board;
import com.base.BoardOperations;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Common figure test functionality
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public abstract class FigureTest {
    @Mock
    protected Board baseBoard;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(baseBoard.getWidth()).thenReturn(5);
    }

    protected void performTest(Figure queen, int[][] checkBoard, int[][] board) {
        queen.fillInBoard(board);
        BoardOperations.printBoard(board);
        for (int i = 0; i < board.length; i++ ) {
            for (int j = 0 ; j < board.length; j ++){
                assertEquals(checkBoard[i][j], board[i][j]);
            }
        }
    }
    protected int[][] getNewBoard() {
        return new int[5][5];
    }
}
