package com.base.board;

import com.base.figures.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for @link{FigureBoard}
 *
 * @author Yuriy Privezentsev
 * @since 10/13/2015
 */
public abstract class FigureBoardTest<T extends FigureBoard> {
    private T figureBoard;

    @Before
    public void init() {
        figureBoard = getFigureBoard(2, 2);
        Position queenPosition = new Position(1, 1, figureBoard);
        figureBoard.addFigure(new Queen(queenPosition));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoval() {
        Position knightPosition = new Position(1, 1, figureBoard);
        figureBoard.removeFigure(new Knight(knightPosition));
    }

    @Test
    public void testTakenField() {
        assertTrue(figureBoard.hasFigure(new Position(1, 1, figureBoard)));
    }

    @Test
    public void testFreeField() {
        assertFalse(figureBoard.hasFigure(new Position(0, 0, figureBoard)));
    }

    @Test
    public void testToString() {
        T figureBoard = getFigureBoard(5, 2);
        figureBoard.addFigure(new Queen(new Position(0, 0, figureBoard)));
        figureBoard.addFigure(new Knight(new Position(0, 1, figureBoard)));
        figureBoard.addFigure(new King(new Position(0, 2, figureBoard)));
        figureBoard.addFigure(new Bishop(new Position(1, 3, figureBoard)));
        figureBoard.addFigure(new Rook(new Position(1, 4, figureBoard)));

        String boardView = "\r\nQ,N,K,-,-\r\n-,-,-,B,R";
        assertEquals(boardView, figureBoard.toString());
    }

    @Test
    public void testDeepCopy() {
        this.figureBoard.addFigure(new Queen(new Position(1, 0, figureBoard)));
        FigureBoard copy = this.figureBoard.deepCopy();

        assertEquals(figureBoard.getClass(),copy.getClass());
        assertEquals(figureBoard.toString(),copy.toString());

    }


    protected abstract T getFigureBoard(int width, int height);
}
