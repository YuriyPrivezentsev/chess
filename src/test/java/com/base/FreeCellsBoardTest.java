package com.base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for @link{FreeCellsBoard}
 *
 * @author Yuriy Privezentsev
 * @since 10/13/2015
 */
public class FreeCellsBoardTest {
    @Test
    public void testFreeCell() {

        FreeCellsBoard freeCellsBoard = new FreeCellsBoard(2,2);
        freeCellsBoard.occupyCell(new Position(0,0, freeCellsBoard));
        Position freeCell = freeCellsBoard.getFirstFreeCell();

        assertEquals(freeCell.getColumn(),1);
        assertEquals(freeCell.getLine(),0);

    }

}
