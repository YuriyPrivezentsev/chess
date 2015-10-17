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
}
