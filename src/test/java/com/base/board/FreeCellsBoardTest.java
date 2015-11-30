package com.base.board;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for @link{FreeCellsBoard}
 *
 * @author Yuriy Privezentsev
 * @since 10/13/2015
 */
public class FreeCellsBoardTest {
    @Test
    public void testGetFirstFreeCell() {

        FreeCellsBoard freeCellsBoard = new FreeCellsBoard(2, 2);
        Position position = new Position(0, 0, freeCellsBoard);
        Collection<Position> takenPositions = new ArrayList<>();
        takenPositions.add(position);
        freeCellsBoard.occupyCells(takenPositions);
        Position freeCell = freeCellsBoard.getFirstFreeCell();

        assertEquals(freeCell.getColumn(), 1);
        assertEquals(freeCell.getLine(), 0);
    }

    @Test
    public void testToString() {
        FreeCellsBoard freeCellsBoard = new FreeCellsBoard(2, 2);
        Position position = new Position(0, 1, freeCellsBoard);
        Collection<Position> takenPositions = new ArrayList<>();
        takenPositions.add(position);
        freeCellsBoard.occupyCells(takenPositions);

        String boardView = "\r\n- X \r\n- - ";
        assertEquals(boardView, freeCellsBoard.toString());
    }

    @Test
    public void testGetNextFreeCell() {
        FreeCellsBoard freeCellsBoard = new FreeCellsBoard(3, 3);
        Position position = new Position(1, 1, freeCellsBoard);
        Position nextFreeCell = freeCellsBoard.getNextFreeCell(position);
        assertEquals(1, nextFreeCell.getLine());
        assertEquals(2, nextFreeCell.getColumn());
    }

    @Test
    public void testFreeCellsCount() {
        FreeCellsBoard freeCellsBoard = new FreeCellsBoard(3, 2);
        assertEquals(6, freeCellsBoard.getFreeCellsCount());

        Collection<Position> positions = new ArrayList<>(3);
        positions.add(new Position(1, 1, freeCellsBoard));
        freeCellsBoard.occupyCells(positions);
        assertEquals(5, freeCellsBoard.getFreeCellsCount());

        positions.clear();
        positions.add(new Position(1, 0, freeCellsBoard));
        positions.add(new Position(1, 1, freeCellsBoard));
        positions.add(new Position(1, 2, freeCellsBoard));
        freeCellsBoard.occupyCells(positions);
        assertEquals(3, freeCellsBoard.getFreeCellsCount());
    }

    @Test
    public void testOccupyCells() {
        FreeCellsBoard freeCellsBoard = new FreeCellsBoard(3, 3);

        Collection<Position> positions = new ArrayList<>(2);
        positions.add(new Position(1,0,freeCellsBoard));
        positions.add(new Position(1, 1, freeCellsBoard));
        positions.add(new Position(1, 2, freeCellsBoard));
        Collection<Position> realCoverage = freeCellsBoard.occupyCells(positions);

        assertEquals(3, realCoverage.size());

        positions.clear();
        positions.add(new Position(1, 1, freeCellsBoard));
        positions.add(new Position(2, 1, freeCellsBoard));
        List<Position> realCoverageList = (List<Position>) freeCellsBoard.occupyCells(positions);
        assertEquals(1, realCoverageList.size());
        assertEquals(2,realCoverageList.get(0).getLine());
        assertEquals(1,realCoverageList.get(0).getColumn());
    }

}
