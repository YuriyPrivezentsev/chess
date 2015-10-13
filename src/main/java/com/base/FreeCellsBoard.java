package com.base;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Contains the list of all free (not taken and not under attack) cells.
 *
 * @author Yuriy Privezentsev
 * @since 10/13/2015
 */
public class FreeCellsBoard {
    private SortedSet<Position> freeCells = new TreeSet<>();
    private final int boardSize;

    public FreeCellsBoard(int boardSize) {
        this.boardSize = boardSize;
        for(int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                freeCells.add(new Position(i,j));
            }
        }
    }

    public void occupyCell(Position occupied){
        freeCells.remove(occupied);
    }

    public Position getFirstFreeCell(){
        return freeCells.first();
    }
}
