package com.base.figures;

import com.base.board.Position;
import org.junit.Test;

/**
 * Unit test for @link{Rook}
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class RookTest extends FigureTest<Rook> {
    @Test
    public void testBoardFillCentral() {
        Position rookPosition = new Position(2, 2, baseBoard);
        Rook rook = new Rook(rookPosition);
        int[][] checkBoard = {{0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0}};
        performTest(rook, checkBoard, 5, 5);
    }

    @Test
    public void testBoardFillSide() {
        Position rookPosition = new Position(1, 0, baseBoard);
        Rook rook = new Rook(rookPosition);
        int[][] checkBoard = {{1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0}};
        performTest(rook, checkBoard, 5, 5);
    }

    @Test
    public void testBoardFillCornerCase() {
        Position rookPosition = new Position(4, 4, baseBoard);
        Rook rook = new Rook(rookPosition);
        int[][] checkBoard = {{0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}};
        performTest(rook, checkBoard, 5, 5);
    }

    @Override
    protected Rook getNewFigure(Position position) {
        return new Rook(position);
    }
}
