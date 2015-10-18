package com.base.board;

import com.base.figures.Figure;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Contains list of figures already placed on the result board.
 *
 * @author Yuriy Privezentsev
 * @since 10/13/2015
 */
public class FigureBoard extends AbstractBoard {
    private SortedMap<Position, Figure> figures = new TreeMap<>();

    public FigureBoard(int width, int height) {
        super(width, height);
    }

    /**
     * Checks whether some of existing is already placed on the cell.
     */
    public boolean hasFigure(Position underAttack) {
        return figures.containsKey(underAttack);
    }

    /**
     * Add figure to result board.
     */
    public void addFigure(Figure figure) {
        figures.put(figure.getPosition(), figure);
    }

    /**
     * Removes figure from the result board.
     */
    public void removeFigure(Figure deleteFigure) {
        Figure removed = figures.remove(deleteFigure.getPosition());
        if (!deleteFigure.equals(removed)) {
            throw new IllegalArgumentException("Trying to delete the wrong figure at position "
                    + deleteFigure.getPosition() + " deleting " + deleteFigure.getName()
                    + " were was " + removed.getName());
        }
    }

    @Override
    public String toString() {
        StringBuilder boardRepresentation = new StringBuilder();
        for (int i = 0; i < getHeight(); i++) {
            boardRepresentation.append("\r\n");
            for (int j = 0; j < getWidth(); j++) {
                Position position = new Position(i, j, this);
                Figure figure = figures.get(position);
                boardRepresentation.append(figure == null ? FREE_POSITION_MARKER : figure.getName());
                boardRepresentation.append(" ");
            }
        }
        return boardRepresentation.toString();
    }
}
