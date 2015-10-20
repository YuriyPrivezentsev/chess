package com.base.figures;

import com.base.board.Board;
import com.base.board.TreeFigureBoard;
import com.base.board.FreeCellsBoard;
import com.base.board.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Common figure test functionality
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public abstract class FigureTest<T extends Figure> {
    private static final Logger LOG = LoggerFactory.getLogger(FigureTest.class);
    @Mock
    protected Board baseBoard;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(baseBoard.getWidth()).thenReturn(5);
    }

    protected void performTest(Figure figure, int[][] checkBoard, int width, int height) {
        TreeFigureBoard figureBoard = new TreeFigureBoard(width, height);
        FreeCellsBoard freeCellsBoard = new FreeCellsBoard(width, height);

        Collection<Position> figureCoverage = figure.placeOnBoard(figureBoard);
        freeCellsBoard.occupyCells(figureCoverage);
        LOG.info(freeCellsBoard.toString());

        for (int line = 0; line < height; line++) {
            for (int column = 0; column < width; column++) {
                boolean isFree = freeCellsBoard.isCellFree(new Position(line, column, figureBoard));
                if (checkBoard[line][column] == 1) {
                    assertFalse(isFree);
                } else {
                    assertTrue(isFree);
                }
            }
        }
    }

    @Test
    public void testSameType() {
        T figureOne = getNewFigure(null);
        T figureTwo = getNewFigure(null);
        assertTrue(figureOne.isSameType(figureTwo));
    }

    @Test
    public void testOverlapping() {
        TreeFigureBoard figureBoard = new TreeFigureBoard(7, 7);
        Position firstPosition = new Position(1, 1, figureBoard);
        T firstFigure = getNewFigure(firstPosition);
        figureBoard.addFigure(firstFigure);

        Position overlappingPosition = getOverlappingPosition(figureBoard);
        T overlappingFigure = getNewFigure(overlappingPosition);
        Collection<Position> overlappingFigurePositions = overlappingFigure.placeOnBoard(figureBoard);

        assertTrue(overlappingFigurePositions.isEmpty());
    }

    protected Position getOverlappingPosition(TreeFigureBoard figureBoard) {
        return new Position(1, 0, figureBoard);
    }

    protected abstract T getNewFigure(Position position);
}
