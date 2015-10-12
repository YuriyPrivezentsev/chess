package com.base.figures;

import org.junit.Test;

/**
 * Unit test for @link{King}
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class KingTest extends FigureTest {
    @Test
    public void testBoardFillCentral() {
        King king = new King(2, 2);
        int [][] checkBoard = { {0,0,0,0,0},
                                {0,1,1,1,0},
                                {0,1,1,1,0},
                                {0,1,1,1,0},
                                {0,0,0,0,0}};
        performTest(king, checkBoard, new int [5][5]);
    }

    @Test
    public void testBoardFillSide() {
        King king = new King(1, 0);
        int [][] checkBoard = { {1,1,0,0,0},
                                {1,1,0,0,0},
                                {1,1,0,0,0},
                                {0,0,0,0,0},
                                {0,0,0,0,0}};
        performTest(king, checkBoard, new int [5][5]);
    }

    @Test
    public void testBoardFillCornerCase() {
        King king = new King(4, 4);
        int [][] checkBoard = { {0,0,0,0,0},
                                {0,0,0,0,0},
                                {0,0,0,0,0},
                                {0,0,0,1,1},
                                {0,0,0,1,1}};
        performTest(king, checkBoard, new int [5][5]);
    }
}
