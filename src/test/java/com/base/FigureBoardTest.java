package com.base;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.base.figures.Knight;
import com.base.figures.Queen;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for @link{FigureBoard}
 *
 * @author Yuriy Privezentsev
 * @since 10/13/2015
 */
public class FigureBoardTest {
    private FigureBoard figureBoard;

    @Before
    public void init(){
        figureBoard = new FigureBoard();
        figureBoard.addFigure(new Queen(1,1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoval() {
        figureBoard.removeFigure(new Knight(1,1));
    }

    @Test
    public void testTakenField() {
        assertTrue(figureBoard.hasFigure(new Position(1,1)));
    }

    @Test
    public void testFreeField() {
        assertFalse(figureBoard.hasFigure(new Position(0,0)));
    }

}
