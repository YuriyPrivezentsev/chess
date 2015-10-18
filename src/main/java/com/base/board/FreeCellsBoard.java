package com.base.board;

import com.base.figures.Figure;

import java.util.*;

/**
 * Contains the list of all free (not taken and not under attack) cells.
 *
 * @author Yuriy Privezentsev
 * @since 10/13/2015
 */
public class FreeCellsBoard extends AbstractBoard {
    public static final String TAKEN_POSITION_MARKER = "X";
    private final NavigableSet<Position> freeCells = new TreeSet<>();

    private FreeCellsBoard(int width, int height, SortedSet<Position> freeCells) {
        super(width, height);
        this.freeCells.addAll(freeCells);
    }

    public FreeCellsBoard(int width, int height) {
        super(width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                freeCells.add(new Position(i, j, this));
            }
        }
    }

    public Collection<Position> occupyCells(Collection<Position> figureCoverage) {
        Collection<Position> realCoverage = new ArrayList<>(figureCoverage.size());
        for (Position position : figureCoverage) {
            if(freeCells.remove(position)){
                realCoverage.add(position);
            }
        }
        return realCoverage;
    }

    public void occupyCell(Position occupied) {
        freeCells.remove(occupied);
    }

    public void freeCells(Collection<Position> figureCoverage){
        freeCells.addAll(figureCoverage);
    }

    public Position getFirstFreeCell() {
        return freeCells.first();
    }

    public Position getNextFreeCell(Position position) {
        return freeCells.higher(position);
    }

    public boolean isCellFree(Position position) {
        return freeCells.contains(position);
    }

    public FreeCellsBoard deepCopy() {
        return new FreeCellsBoard(getWidth(), getHeight(), freeCells);
    }

    @Override
    public String toString() {
        StringBuilder boardRepresentation = new StringBuilder();
        for (int i = 0; i < getHeight(); i++) {
            boardRepresentation.append("\r\n");
            for (int j = 0; j < getWidth(); j++) {
                Position position = new Position(i, j, this);
                boardRepresentation.append(freeCells.contains(position) ? FREE_POSITION_MARKER : TAKEN_POSITION_MARKER);
                boardRepresentation.append(" ");
            }
        }
        return boardRepresentation.toString();
    }

    public int getFreeCellsCount() {
        return freeCells.size();
    }
}
