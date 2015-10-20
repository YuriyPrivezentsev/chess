package com.base.board;

import com.base.figures.Figure;

import java.util.ArrayList;
import java.util.List;

/**
 * The result board implementation based on Arrays.
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public class ArrayFigureBoard extends AbstractFigureBoard {
    private final List<List<Figure>> cells;

    public ArrayFigureBoard(int width, int height) {
        super(width, height);
        cells = new ArrayList<>(height);
        for (int line = 0; line < height; line++) {
            List<Figure> lineRepresentation = new ArrayList<>(width);
            for (int column = 0; column < width; column++) {
                lineRepresentation.add(null);
            }
            cells.add(lineRepresentation);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasFigure(Position underAttack) {
        return cells.get(underAttack.getLine()).get(underAttack.getColumn()) != null;
    }

    @Override
    protected Figure tryToInsert(Figure figure) {
        Position position = figure.getPosition();
        List<Figure> line = cells.get(position.getLine());

        Figure previousFigure = line.get(position.getColumn());

        line.set(position.getColumn(), figure);

        return previousFigure;
    }

    @Override
    protected Figure tryToRemove(Figure figure) {
        Position position = figure.getPosition();
        List<Figure> line = cells.get(position.getLine());
        Figure previousFigure = line.get(position.getColumn());
        line.set(position.getColumn(), null);
        return previousFigure;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder boardRepresentation = new StringBuilder();
        for (List<Figure> line : cells) {
            boardRepresentation.append("\r\n");
            for (Figure figure : line) {
                boardRepresentation.append(figure == null ? FREE_POSITION_MARKER : figure.getName());
                boardRepresentation.append(" ");
            }
            boardRepresentation.delete(boardRepresentation.length() - 1, boardRepresentation.length());
        }
        return boardRepresentation.toString();
    }
}
