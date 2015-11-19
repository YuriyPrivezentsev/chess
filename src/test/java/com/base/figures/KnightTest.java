package com.base.figures;

import com.base.board.TreeFigureBoard;
import com.base.board.Position;
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
public class KnightTest extends FigureTest<Knight> {
    @Before
    @Override
    public void init() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(baseBoard.getWidth()).thenReturn(7);
    }

    @Override
    protected Position getOverlappingPosition(TreeFigureBoard figureBoard) {
        return new Position(2, 3, figureBoard);
    }

    @Override
    protected Knight getNewFigure(Position position) {
        return new Knight(position);
    }

    @Test
    public void testUpperBound() {
        TreeFigureBoard figureBoard = new TreeFigureBoard(4, 4);
        Position knightPosition = new Position(0, 2, figureBoard);
        Knight knight = new Knight(knightPosition);
        int[][] checkBoard ={
                {0,0,1,0},
                {1,0,0,0},
                {0,1,0,1},
                {0,0,0,0}
        };
        performTest(knight, checkBoard, 4, 4);
    }

    @Test
    public void testRightBound() {
        TreeFigureBoard figureBoard = new TreeFigureBoard(4, 4);
        Position knightPosition = new Position(1, 3, figureBoard);
        Knight knight = new Knight(knightPosition);
        int[][] checkBoard ={
                {0,1,0,0},
                {0,0,0,1},
                {0,1,0,0},
                {0,0,1,0}
        };
        performTest(knight, checkBoard, 4, 4);
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
        performTest(knight, checkBoard, 7, 7);
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
        performTest(knight, checkBoard, 7, 7);
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
        performTest(knight, checkBoard, 7, 7);
    }

    @Override
    protected Figure.Type getExpectedFigureType() {
        return Figure.Type.KNIGHT;
    }

    @Override
    protected String getExpectedBoardSymbol() {
        return "N";
    }
}
