package com.base;

import com.base.figures.Figure;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Contains list of figures already placed on board
 *
 * @author Yuriy Privezentsev
 * @since 10/13/2015
 */
public class FigureBoard extends AbstractBoard{
    private SortedMap<Position, Figure> figures = new TreeMap<>();

    public FigureBoard(int width, int height) {
        super(width, height);
    }

    public boolean hasFigure(Position underAttack){
        return figures.containsKey(underAttack);
    }

    public void addFigure(Figure figure){
        figures.put(figure.getPosition(),figure);
    }

    public void removeFigure(Figure deleteFigure){
        Figure removed = figures.remove(deleteFigure.getPosition());
        if(!deleteFigure.equals(removed)){
            throw new IllegalArgumentException("Trying to delete the wrong figure at position "
                    + deleteFigure.getPosition() + " deleting " + deleteFigure.getName()
                    + " were was " + removed.getName());
        }
    }

    @Override
    public String toString() {
        StringBuilder boardRepresentation = new StringBuilder();
        for (int i = 0; i < getHeight(); i++){
            boardRepresentation.append("\r\n");
            for (int j = 0; j < getWidth(); j++){
                Position position = new Position(i, j, this);
                Figure figure = figures.get(position);
                boardRepresentation.append(figure == null ? FREE_POSITION_MARKER : figure.getName());
                boardRepresentation.append(" ");
            }
        }
        return boardRepresentation.toString();
    }
}
