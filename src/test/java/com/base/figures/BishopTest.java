package com.base.figures;

import org.junit.Test;

/**
 * Unit test for @link{Bishop}
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class BishopTest extends FigureTest {
    @Test
    public void testBoardFillCentral() {
        Bishop bishop = new Bishop(2, 2);
        int[][] checkBoard = {{1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1}};
        performTest(bishop, checkBoard, new int[5][5]);
    }

    @Test
    public void testBoardFillSide() {
        Bishop bishop = new Bishop(1, 0);
        int[][] checkBoard = {{0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0}};
        performTest(bishop, checkBoard, new int[5][5]);
    }

    @Test
    public void testBoardFillCornerCase() {
        Bishop bishop = new Bishop(4, 4);
        int[][] checkBoard = {{1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1}};
        performTest(bishop, checkBoard, new int[5][5]);
    }
}
