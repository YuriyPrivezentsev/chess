package com.base.figures;

import com.base.FigureBoard;
import com.base.Position;
import org.junit.Test;

/**
 * Unit test for @link{Bishop}
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class BishopTest extends FigureTest<Bishop> {

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
        performTest(bishop, checkBoard, 5, 5);
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
        performTest(bishop, checkBoard, 5, 5);
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
        performTest(bishop, checkBoard, 5, 5);
    }

    @Override
    protected Position getOverlappingPosition(FigureBoard figureBoard) {
        return new Position(3,3,figureBoard);
    }

    @Override
    protected Bishop getNewFigure(Position position) {
        return new Bishop(position);
    }
}
