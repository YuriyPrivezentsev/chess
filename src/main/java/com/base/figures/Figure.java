package com.base.figures;

import com.base.board.FigureBoard;
import com.base.board.Position;

import java.util.Collection;

/**
 * Interface representing chess figure.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public interface Figure extends Comparable<Figure> {
    enum Type {
        KING("K"),
        QUEEN("Q"),
        KNIGHT("N"),
        ROOK("R"),
        BISHOP("B");

        private final String boardSymbol;

        Type(String boardSymbol) {
            this.boardSymbol = boardSymbol;
        }

        public String getBoardSymbol() {
            return boardSymbol;
        }

        public static Type fromBoardSymbol(String boardSymbol) {
            for (Type type : Type.values()) {
                if (type.getBoardSymbol().equals(boardSymbol)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("There is no figure with such notation: " + boardSymbol);
        }
    }

    /**
     * Try to position figure on board making sure it neither can be taken nor can take other figures on board.
     *
     * @param resultBoard - the board with figures placed
     * @return - The collection of positions which can be attacked by the figure, including its own place. Empty
     * collection if the figure cannot be placed.
     */
    Collection<Position> placeOnBoard(FigureBoard resultBoard);

    /**
     * Current figure position.
     */
    Position getPosition();

    /**
     * Set current figure position on board
     */
    void setPosition(Position position);

    /**
     * Get the priority in which to process the figures.
     * The priorities are chosen based on the combination of
     * complexity of figure processing and number of cells occupied on board.
     */
    int getPriority();

    /**
     * Gets the letter mark for the figure
     */
    String getBoardSymbol();

    /**
     * Get figure type
     */
    Type getType();

    /**
     * Test whether the current figure is of the same class as the other one.
     */
    boolean isSameType(Figure other);

    /**
     * Creates the deep copy of the figure, including the position.
     */
    Figure deepCopy();
}
