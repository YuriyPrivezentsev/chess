package com.base.board;

import com.base.figures.Figure;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The result board implementation based on Sorted map. All the existing values are stored in the sorted map keyed by
 * figure positions.
 *
 * @author Yuriy Privezentsev
 * @since 10/13/2015
 */
public class TreeFigureBoard extends AbstractFigureBoard {
    private SortedMap<Position, Figure> figures = new TreeMap<>();

    public TreeFigureBoard(int width, int height) {
        super(width, height);
    }

    @Override
    protected Figure tryToInsert(Figure figure) {
        return figures.put(figure.getPosition(),figure);
    }

    @Override
    protected Figure tryToRemove(Figure figure) {
        return figures.remove(figure.getPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasFigure(Position underAttack) {
        return figures.containsKey(underAttack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder boardRepresentation = new StringBuilder();
        for (int line = 0; line < getHeight(); line++) {
            boardRepresentation.append("\r\n");
            for (int column = 0; column < getWidth(); column++) {
                Position position = new Position(line, column, this);
                Figure figure = figures.get(position);
                boardRepresentation.append(figure == null ? FREE_POSITION_MARKER : figure.getBoardSymbol());
                boardRepresentation.append(POSITION_SEPARATOR);
            }
            boardRepresentation.delete(boardRepresentation.length()-1,boardRepresentation.length());
        }
        return boardRepresentation.toString();
    }

    @Override
    public FigureBoard deepCopy() {
        TreeFigureBoard result = new TreeFigureBoard(getWidth(), getHeight());
        for (Figure figure : figures.values()) {
            result.addFigure(figure.deepCopy());
        }
        return result;
    }
}
