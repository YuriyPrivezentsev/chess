package com.base.figures;

import org.junit.Test;

/**
 * Unit test for @link{Queen}
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class QueenTest extends FigureTest {
    @Test
    public void testBoardFillCentral() {
        Queen queen = new Queen(2, 2);
        int [][] checkBoard = { {1,0,1,0,1},
                                {0,1,1,1,0},
                                {1,1,1,1,1},
                                {0,1,1,1,0},
                                {1,0,1,0,1}};
        performTest(queen, checkBoard, new int [5][5]);
    }

    @Test
    public void testBoardFillSide() {
        Queen queen = new Queen(1, 0);
        int [][] checkBoard = { {1,1,0,0,0},
                                {1,1,1,1,1},
                                {1,1,0,0,0},
                                {1,0,1,0,0},
                                {1,0,0,1,0}};
        performTest(queen, checkBoard, new int [5][5]);
    }

    @Test
    public void testBoardFillCornerCase() {
        Queen queen = new Queen(4, 4);
        int [][] checkBoard = { {1,0,0,0,1},
                                {0,1,0,0,1},
                                {0,0,1,0,1},
                                {0,0,0,1,1},
                                {1,1,1,1,1}};
        performTest(queen, checkBoard, new int [5][5]);
    }
}
