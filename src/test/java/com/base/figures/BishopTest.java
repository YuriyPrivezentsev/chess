package com.base.figures;

import com.base.Position;
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
        Position bishopPosition = new Position(2, 2, baseBoard);
        Bishop bishop = new Bishop(bishopPosition);
        int[][] checkBoard = {
                {1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1}};
        performTest(bishop, checkBoard, getNewBoard());
    }

    @Test
    public void testBoardFillSide() {
        Position bishopPosition = new Position(1, 0, baseBoard);
        Bishop bishop = new Bishop(bishopPosition);
        int[][] checkBoard = {
                {0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0}};
        performTest(bishop, checkBoard, getNewBoard());
    }

    @Test
    public void testBoardFillCornerCase() {
        Position bishopPosition = new Position(4, 4, baseBoard);
        Bishop bishop = new Bishop(bishopPosition);
        int[][] checkBoard = {
                {1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1}};
        performTest(bishop, checkBoard, getNewBoard());
    }
}
