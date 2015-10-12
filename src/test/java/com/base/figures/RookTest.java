package com.base.figures;

import org.junit.Test;

/**
 * Unit test for @link{Rook}
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class RookTest extends FigureTest {
    @Test
    public void testBoardFillCentral() {
        Rook rook = new Rook(2, 2);
        int [][] checkBoard = { {0,0,1,0,0},
                                {0,0,1,0,0},
                                {1,1,1,1,1},
                                {0,0,1,0,0},
                                {0,0,1,0,0}};
        performTest(rook, checkBoard, new int [5][5]);
    }

    @Test
    public void testBoardFillSide() {
        Rook rook = new Rook(1, 0);
        int [][] checkBoard = { {1,0,0,0,0},
                                {1,1,1,1,1},
                                {1,0,0,0,0},
                                {1,0,0,0,0},
                                {1,0,0,0,0}};
        performTest(rook, checkBoard, new int [5][5]);
    }

    @Test
    public void testBoardFillCornerCase() {
        Rook rook = new Rook(4, 4);
        int [][] checkBoard = { {0,0,0,0,1},
                                {0,0,0,0,1},
                                {0,0,0,0,1},
                                {0,0,0,0,1},
                                {1,1,1,1,1}};
        performTest(rook, checkBoard, new int [5][5]);
    }
}
