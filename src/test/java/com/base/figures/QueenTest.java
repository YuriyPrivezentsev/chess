package com.base.figures;

import com.base.board.Position;
import org.junit.Test;

/**
 * Unit test for @link{Queen}
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class QueenTest extends FigureTest<Queen> {
    @Test
    public void testBoardFillCentral() {
        Position queenPosition = new Position(2, 2, baseBoard);
        Queen queen = new Queen(queenPosition);
        int[][] checkBoard = {{1, 0, 1, 0, 1},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 0},
                {1, 0, 1, 0, 1}};
        performTest(queen, checkBoard, 5, 5);
    }

    @Test
    public void testBoardFillSide() {
        Position queenPosition = new Position(1, 0, baseBoard);
        Queen queen = new Queen(queenPosition);
        int[][] checkBoard = {{1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {1, 0, 0, 1, 0}};
        performTest(queen, checkBoard, 5, 5);
    }

    @Test
    public void testBoardFillCornerCase() {
        Position queenPosition = new Position(4, 4, baseBoard);
        Queen queen = new Queen(queenPosition);
        int[][] checkBoard = {{1, 0, 0, 0, 1},
                {0, 1, 0, 0, 1},
                {0, 0, 1, 0, 1},
                {0, 0, 0, 1, 1},
                {1, 1, 1, 1, 1}};
        performTest(queen, checkBoard, 5, 5);
    }

    @Override
    protected Queen getNewFigure(Position position) {
        return new Queen(position);
    }

    @Override
    protected Figure.Type getExpectedFigureType() {
        return Figure.Type.QUEEN;
    }
}
