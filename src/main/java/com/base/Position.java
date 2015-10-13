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

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
        weightOnBoard = line * getBoardSize() + column;
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

    private int getBoardSize() {
        //TODO:2015-10-13:yuriy.privezentsev: replace the hardcode with proper size retrieval.
        return 10;
    }

    @Override
    public int compareTo(Position other) {
        return getWeightOnBoard() - other.getWeightOnBoard();
    }
}
