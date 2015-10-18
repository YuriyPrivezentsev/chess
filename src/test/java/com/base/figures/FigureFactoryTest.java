package com.base.figures;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for @link{FigureFactory}
 *
 * @author Yuriy Privezentsev
 * @since 10/18/2015
 */
public class FigureFactoryTest {
    private FigureFactory figureFactory = new FigureFactory();

    @Test
    public void testGetQueen() {
        Figure figure = figureFactory.getFigure("Q");
        assertEquals(Queen.class, figure.getClass());
    }

    @Test
    public void testGetKing() {
        Figure figure = figureFactory.getFigure("K");
        assertEquals(King.class, figure.getClass());
    }

    @Test
    public void testGetKnight() {
        Figure figure = figureFactory.getFigure("N");
        assertEquals(Knight.class, figure.getClass());
    }

    @Test
    public void testGetBishop() {
        Figure figure = figureFactory.getFigure("B");
        assertEquals(Bishop.class, figure.getClass());
    }

    @Test
    public void testGetRook() {
        Figure figure = figureFactory.getFigure("R");
        assertEquals(Rook.class, figure.getClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWrongFigure() {
        figureFactory.getFigure("Test");
    }

    @Test
    public void testGetTwoQeens() {
        Collection<Figure> setOfFigures = figureFactory.createSetOfFigures("2XQ");
        assertEquals(2, setOfFigures.size());
        for (Figure figure : setOfFigures) {
            assertEquals(Queen.class, figure.getClass());
        }
    }

    @Test
    public void testGetKnightThreeTimes() {
        Collection<Figure> setOfFigures = figureFactory.createSetOfFigures("NX3");
        assertEquals(3, setOfFigures.size());
        for (Figure figure : setOfFigures) {
            assertEquals(Knight.class, figure.getClass());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongNumberOfFigures() {
        figureFactory.createSetOfFigures("-1XQ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeNumberOfFigures() {
        figureFactory.createSetOfFigures("QX-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnrecognizedFormat() {
        figureFactory.createSetOfFigures("QX1X1");
    }
}
