package com.base.figures;

import com.base.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Unit test for @link{Knight}
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class KnightTest extends FigureTest {
    @Before
    @Override
    public void init() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(baseBoard.getWidth()).thenReturn(7);
    }

    @Override
    protected int[][] getNewBoard() {
        return new int[7][7];
    }

    @Test
    public void testBoardFillCentral() {
        Position knightPosition = new Position(3, 3, baseBoard);
        Knight knight = new Knight(knightPosition);
        int[][] checkBoard = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}};
        performTest(knight, checkBoard, getNewBoard());
    }

    @Test
    public void testBoardFillSide() {
        Position knightPosition = new Position(1, 0, baseBoard);
        Knight knight = new Knight(knightPosition);
        int[][] checkBoard = {
                {0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}};
        performTest(knight, checkBoard, getNewBoard());
    }

    @Test
    public void testBoardFillCornerCase() {
        Position knightPosition = new Position(6, 6, baseBoard);
        Knight knight = new Knight(knightPosition);
        int[][] checkBoard = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1}};
        performTest(knight, checkBoard, getNewBoard());
    }
}
