package com.base;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Contains the list of all free (not taken and not under attack) cells.
 *
 * @author Yuriy Privezentsev
 * @since 10/13/2015
 */
public class FreeCellsBoard extends AbstractBoard {
    public static final String TAKEN_POSITION_MARKER = "X";
    private SortedSet<Position> freeCells = new TreeSet<>();

    public FreeCellsBoard(int width, int height) {
        super(width,height);
        for(int i = 0; i < height; i++){
            for (int j= 0; j < width; j++){
                freeCells.add(new Position(i,j, this));
            }
        }
    }

    public void occupyCell(Position occupied){
        freeCells.remove(occupied);
    }

    public Position getFirstFreeCell(){
        return freeCells.first();
    }

    @Override
    public String toString() {
        StringBuilder boardRepresentation = new StringBuilder();
        for (int i = 0; i < getHeight(); i++){
            boardRepresentation.append("\r\n");
            for(int j = 0; j < getWidth(); j++){
                Position position = new Position(i, j, this);
                boardRepresentation.append(freeCells.contains(position) ? FREE_POSITION_MARKER : TAKEN_POSITION_MARKER);
                boardRepresentation.append(" ");
            }
        }
        return boardRepresentation.toString();
    }
}