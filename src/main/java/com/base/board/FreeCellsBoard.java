package com.base.board;

import com.base.figures.Figure;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Contains the list of all free (not taken and not under attack) cells.
 *
 * @author Yuriy Privezentsev
 * @since 10/13/2015
 */
public class FreeCellsBoard extends AbstractBoard {
    public static final String TAKEN_POSITION_MARKER = "X";
    private final NavigableSet<Position> freeCells = new TreeSet<>();

    public FreeCellsBoard(int width, int height) {
        super(width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                freeCells.add(new Position(i, j, this));
            }
        }
    }

    /**
     * Mark cells as taken when they are under attack or occupied by some figure.
     * @param figureCoverage - all cells which must be marked
     * @return - cells which indeed have been marked
     */
    public Collection<Position> occupyCells(Collection<Position> figureCoverage) {
        Collection<Position> realCoverage = new ArrayList<>(figureCoverage.size());
        realCoverage.addAll(figureCoverage.stream().filter(freeCells::remove).collect(Collectors.toList()));
        return realCoverage;
    }

    /**
     * Unmark cells as under attack or occupied.
     * @param figureCoverage - cells to be unmarked
     */
    public void freeCells(Collection<Position> figureCoverage){
        freeCells.addAll(figureCoverage);
    }

    /**
     * Retrieve first cell on board not marked as occupied.
     * @return - first free cell or null if none.
     */
    public Position getFirstFreeCell() {
        return freeCells.first();
    }

    /**
     * Returns the free cell next to the position of the figure provided.
     * If figure has no position then it returns first free cell.
     * @param figure - figure to count from
     * @return - first free cell in regards to present figure position or null if none.
     */
    public Position getNextFreeCell(Figure figure){
        Position position = figure.getPosition();

        if(position == null){
            return getFirstFreeCell();
        }

        return getNextFreeCell(position);
    }

    /**
     * Retrieve first cell after given not marked as occupied.
     * @param position - cell to start with.
     * @return - first free cell after given or null if none.
     */
    public Position getNextFreeCell(Position position) {
        return freeCells.higher(position);
    }

    /**
     * Test whether cell is marked as under attack or occupied
     */
    public boolean isCellFree(Position position) {
        return freeCells.contains(position);
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

    /**
     * Get count of cells neither marked as under attack nor occupied
     */
    public int getFreeCellsCount() {
        return freeCells.size();
    }
}
