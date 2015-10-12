package com.base.figures;

import org.junit.Test;

/**
 * Unit test for @link{Knight}
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class KnightTest extends FigureTest {
    @Test
    public void testBoardFillCentral() {
        Knight knight = new Knight(3, 3);
        int[][] checkBoard = {{0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}};
        performTest(knight, checkBoard, new int[7][7]);
    }

    @Test
    public void testBoardFillSide() {
        Knight knight = new Knight(1, 0);
        int[][] checkBoard = {{0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}};
        performTest(knight, checkBoard, new int[7][7]);
    }

    @Test
    public void testBoardFillCornerCase() {
        Knight knight = new Knight(6, 6);
        int[][] checkBoard = {{0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1}};
        performTest(knight, checkBoard, new int[7][7]);
    }
}
