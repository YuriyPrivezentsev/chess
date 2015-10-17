package com.base.figures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.base.*;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Common figure test functionality
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public abstract class FigureTest {
    private static final Logger LOG = LoggerFactory.getLogger(FigureTest.class);
    @Mock
    protected Board baseBoard;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(baseBoard.getWidth()).thenReturn(5);
    }

    protected void performTest(Figure queen, int[][] checkBoard, int width, int height) {
        FigureBoard figureBoard = new FigureBoard(width, height);
        FreeCellsBoard freeCellsBoard = new FreeCellsBoard(width, height);

        Collection<Position> figureCoverage = queen.placeOnBoard(figureBoard);
        freeCellsBoard.occupyCells(figureCoverage);
        LOG.info(freeCellsBoard.toString());

        for (int line = 0; line < height; line++ ) {
            for (int column = 0 ; column < width; column ++){
                boolean isFree = freeCellsBoard.isCellFree(new Position(line, column, figureBoard));
                if(checkBoard[line][column] == 1){
                    assertFalse(isFree);
                } else {
                    assertTrue(isFree);
                }
            }
        }
    }
}
