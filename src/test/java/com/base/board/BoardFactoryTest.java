package com.base.board;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for @link{FigureBoard}
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public class BoardFactoryTest {
    BoardFactory boardFactory = new BoardFactory(3, 2);

    @Test
    public void testSize() {
        assertEquals(6, boardFactory.getTotalCellCount());

        String expectedBoard = "\r\n- - -\r\n- - -";
        FigureBoard figureBoard = boardFactory.getFigureBoard();
        assertEquals(expectedBoard, figureBoard.toString());
    }

    @Test
    public void testArrayFigureBoard() {
        FigureBoard figureBoard = boardFactory.getFigureBoard(BoardFactory.FigureBoardType.ARRAY);
        assertEquals(ArrayFigureBoard.class, figureBoard.getClass());
    }

    @Test
    public void testTreeFigureBoard() {
        FigureBoard figureBoard = boardFactory.getFigureBoard(BoardFactory.FigureBoardType.TREE);
        assertEquals(TreeFigureBoard.class, figureBoard.getClass());
    }

}
