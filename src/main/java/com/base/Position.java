package com.base;

/**
 * Figure position on the board.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class Position implements Comparable<Position>{
    private final int line; //line
    private final int column; //column
    private final int weightOnBoard;

    public Position(int line, int column, Board board) {
        this.line = line;
        this.column = column;
        weightOnBoard = line * board.getWidth()+ column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public int getWeightOnBoard(){
        return weightOnBoard;
    }

    @Override
    public int compareTo(Position other) {
        if(other == null ){
            throw new NullPointerException("Position cannot be null");
        }
        return getWeightOnBoard() - other.getWeightOnBoard();
    }
}
