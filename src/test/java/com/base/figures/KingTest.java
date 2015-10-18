package com.base.figures;

import com.base.board.Position;
import org.junit.Test;

/**
 * Unit test for @link{King}
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class KingTest extends FigureTest<King> {
    @Test
    public void testBoardFillCentral() {
        Position kingPosition = new Position(2, 2, baseBoard);
        King king = new King(kingPosition);
        int[][] checkBoard = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}};
        performTest(king, checkBoard, 5, 5);
    }

    @Test
    public void testBoardFillSide() {
        Position kingPosition = new Position(1, 0, baseBoard);
        King king = new King(kingPosition);
        int[][] checkBoard = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        performTest(king, checkBoard, 5, 5);
    }

    @Test
    public void testBoardFillUpperBound() {
        Position kingPosition = new Position(0, 3, baseBoard);
        King king = new King(kingPosition);
        int[][] checkBoard = {
                {0, 0, 1, 1, 1},
                {0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        performTest(king, checkBoard, 5, 5);
    }

    @Test
    public void testBoardFillRightBound() {
        Position kingPosition = new Position(1, 4, baseBoard);
        King king = new King(kingPosition);
        int[][] checkBoard = {
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        performTest(king, checkBoard, 5, 5);
    }

    @Test
    public void testBoardFillCornerCase() {
        Position kingPosition = new Position(4, 4, baseBoard);
        King king = new King(kingPosition);
        int[][] checkBoard = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}};
        performTest(king, checkBoard, 5, 5);
    }

    @Override
    protected King getNewFigure(Position position) {
        return new King(position);
    }
}
