package com.base.figures;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for @link{FigureFactory}
 *
 * @author Yuriy Privezentsev
 * @since 10/18/2015
 */
public class FigureFactoryTest {
    @Test
    public void testGetQueen() {
        Figure figure = FigureFactory.getFigure("Q");
        assertEquals(Queen.class, figure.getClass());
    }

    @Test
    public void testGetKing() {
        Figure figure = FigureFactory.getFigure("K");
        assertEquals(King.class, figure.getClass());
    }

    @Test
    public void testGetKnight() {
        Figure figure = FigureFactory.getFigure("N");
        assertEquals(Knight.class, figure.getClass());
    }

    @Test
    public void testGetBishop() {
        Figure figure = FigureFactory.getFigure("B");
        assertEquals(Bishop.class, figure.getClass());
    }

    @Test
    public void testGetRook() {
        Figure figure = FigureFactory.getFigure("R");
        assertEquals(Rook.class, figure.getClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWrongFigure() {
        FigureFactory.getFigure("Test");
    }

    @Test
    public void testGetTwoQeens() {
        Collection<Figure> setOfFigures = FigureFactory.createSetOfFigures("2xQ");
        assertEquals(2, setOfFigures.size());
        for (Figure figure : setOfFigures) {
            assertEquals(Queen.class, figure.getClass());
        }
    }

    @Test
    public void testGetKnightThreeTimes() {
        Collection<Figure> setOfFigures = FigureFactory.createSetOfFigures("Nx3");
        assertEquals(3,setOfFigures.size());
        for (Figure figure : setOfFigures) {
            assertEquals(Knight.class, figure.getClass());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongNumberOfFigures() {
        FigureFactory.createSetOfFigures("-1xQ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeNumberOfFigures() {
        FigureFactory.createSetOfFigures("Qx-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnrecognizedFormat() {
        FigureFactory.createSetOfFigures("Qx1x1");
    }
}
